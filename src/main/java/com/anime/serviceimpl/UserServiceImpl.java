package com.anime.serviceimpl;

import com.anime.constants.AnimeConstants;
import com.anime.dao.UserDao;
import com.anime.entity.Users;
import com.anime.service.UserService;
import com.anime.utils.AnimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    //Metodo de registro de usuarios
    @Override
    public ResponseEntity<String> registro(Map<String, String> requestMap) {
        log.info("inside registro {}", requestMap);
        try {
            if (validaResgistroMap(requestMap)){
                Users users = userDao.findByEmailId(requestMap.get("uemail")); //validamos q el email no se repita
                if (Objects.isNull(users)){
                    userDao.save(getUsersFromMap(requestMap));//se llama al get user para hacer la insercion de los datos
                    return AnimeUtils.getResponseEntity("Registro Exitoso",HttpStatus.OK);
                }else {
                    return AnimeUtils.getResponseEntity("Email ya existe",HttpStatus.BAD_REQUEST);
                }
            }else {
                return AnimeUtils.getResponseEntity(AnimeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);//para dicrile al usuario q no inseto todos los datos
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return AnimeUtils.getResponseEntity(AnimeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Metodo para validar que los campos del registro contengan informacion
    private boolean validaResgistroMap(Map<String, String> resquestMap){
       if(resquestMap.containsKey("unombre") && resquestMap.containsKey("uapellido") && resquestMap.containsKey("uemail")
                && resquestMap.containsKey("upassword")){
           return true;
       }else
           return false;
    }

    //metodo para obtener la informacion suminstrada y enviarlos al emtoo de registro
    private Users getUsersFromMap(Map<String, String> requestMap){
        Users users = new Users();
        users.setUnombre(requestMap.get("unombre"));
        users.setUapellido(requestMap.get("uapellido"));
        users.setUemail(requestMap.get("uemail"));
        users.setUpassword(requestMap.get("upassword"));
        users.setStatus("false");
        users.setUrole("USER");
        return users;
    }
}
