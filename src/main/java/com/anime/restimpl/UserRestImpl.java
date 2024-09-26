package com.anime.restimpl;

import com.anime.constants.AnimeConstants;
import com.anime.rest.UserRest;
import com.anime.service.UserService;
import com.anime.utils.AnimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> registro(Map<String, String> requestMap) {
        try {
            userService.registro(requestMap);
        }catch (Exception ex){
            ex.printStackTrace(); //para devolver un mensaje de error personalizado
        }
        return AnimeUtils.getResponseEntity(AnimeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
