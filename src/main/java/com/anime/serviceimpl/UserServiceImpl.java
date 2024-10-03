package com.anime.serviceimpl;

import com.anime.JWT.JwtUtil;
import com.anime.dao.UserDao;
import com.anime.dto.ReqRes;
import com.anime.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Metodo para registrar un usuario
    public ReqRes register(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();
        try {
            Users users = new Users();
            users.setUemail(registrationRequest.getUemail());
            users.setUrole(registrationRequest.getUrole());
            users.setUnombre(registrationRequest.getUnombre());
            users.setUapellido(registrationRequest.getUapellido());
            users.setUpassword(passwordEncoder.encode(registrationRequest.getUpassword()));
            Users UsersResult = userDao.save(users);

            if (UsersResult.getUid()>0){
                resp.setUsers((UsersResult));
                resp.setMessage("Nuevo usuario creado");
                resp.setStatusCode(200);
            }
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public ReqRes login(ReqRes logonRequest) {
        ReqRes response = new ReqRes();
        try {
            // Autenticar al usuario
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    logonRequest.getUemail(), logonRequest.getUpassword()));

            // Obtener el usuario autenticado
            var user = userDao.findByUemail(logonRequest.getUemail()).orElseThrow();

            // Verificar que el usuario esté activo
            if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
                // Cambiar el estado a "ACTIVE"
                user.setStatus("ACTIVE");
                userDao.save(user); // Asegúrate de que tu UserDao tiene este método
            }

            // Generar tokens
            var jwt = jwtUtil.generateSecretToken(user);
            var refreshToken = jwtUtil.generateRefreshToken(new HashMap<>(), user);

            response.setStatusCode(200);
            response.setToken(jwt);
            response.setUrole(user.getUrole());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Inicio de sesión exitoso");

        } catch (RuntimeException e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }


    public ReqRes refreshToken(ReqRes refreshTokenrequest){
        ReqRes response = new ReqRes();
        try{
            String Email = jwtUtil.extractUsername(refreshTokenrequest.getToken());
            Users users = userDao.findByUemail(Email).orElseThrow();
            if (jwtUtil.isTokenValid(refreshTokenrequest.getToken(), users)) {
                var jwt = jwtUtil.generateSecretToken(users);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenrequest.getToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Token actualizado");
            }
            response.setStatusCode(200);
            return response;

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    public ReqRes getAllUsers() {
        ReqRes reqRes = new ReqRes();
        try {
            List<Users> result = userDao.findAll();
            if (!result.isEmpty()) {
                reqRes.setUsersList(result);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Exitosa");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No se encontraron usuarios");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Se produjo un error: " + e.getMessage());
            return reqRes;
        }
    }


    public ReqRes getUsersById(Integer Uid) {
        ReqRes reqRes = new ReqRes();
        try {
            Users usersById = userDao.findById(Uid).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            reqRes.setUsers(usersById);
            reqRes.setStatusCode(200);
            reqRes.setMessage("Usuarios con id '" + Uid + "' encontrado exitosamente");
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Se produjo un error: " + e.getMessage());
        }
        return reqRes;
    }


    public ReqRes deleteUser(Integer userUid) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<Users> userOptional = userDao.findById(userUid);
            if (userOptional.isPresent()) {
                userDao.deleteById(userUid);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Usuario eliminado exitosamente");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("Usuario no encontrado para eliminación");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Se produjo un error al eliminar el usuario: " + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes updateUser(Integer userUid, Users updatedUser) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<Users> userOptional = userDao.findById(userUid);
            if (userOptional.isPresent()) {
                Users existingUser = userOptional.get();
                existingUser.setUemail(updatedUser.getUemail());
                existingUser.setUnombre(updatedUser.getUnombre());
                existingUser.setUapellido(updatedUser.getUapellido());
                existingUser.setUrole(updatedUser.getUrole());

                // Check if password is present in the request
                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                    // Encode the password and update it
                    existingUser.setUpassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                Users savedUser = userDao.save(existingUser);
                reqRes.setUsers(savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Usuario actualizado con éxito");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("Usuario no encontrado para actualización");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Ocurrió un error al actualizar el usuario: " + e.getMessage());
        }
        return reqRes;
    }


    public ReqRes getMyInfo(String uemail){
        ReqRes reqRes = new ReqRes();
        try {
            Optional<Users> userOptional = userDao.findByUemail(uemail);
            if (userOptional.isPresent()) {
                reqRes.setUsers(userOptional.get());
                reqRes.setStatusCode(200);
                reqRes.setMessage("exitoso");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("Usuario no encontrado para actualización");
            }

        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage("Se produjo un error al obtener la información del usuario: " + e.getMessage());
        }
        return reqRes;
    }
}
