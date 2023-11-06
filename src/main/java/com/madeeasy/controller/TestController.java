package com.madeeasy.controller;

import com.madeeasy.model.UserModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/anonymous")
    public ResponseEntity<String> getAnonymous() {
        return ResponseEntity.ok("Hello Anonymous");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('real_admin')")
    public ResponseEntity<String> getAdmin(Principal principal) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        Map<String, Object> tokenAttributes = token.getTokenAttributes();

        String userName = (String) tokenAttributes.get("preferred_username");
        String userEmail = (String) tokenAttributes.get("email");
        return ResponseEntity.ok("Hello Admin \nUser Name : " + userName + "\nUser Email : " + userEmail);
    }

    /**
     * use header :
     * Accept : application/json -> for json
     * -----------OR-----------
     * Accept : application/xml -> for xml
     */
    @GetMapping(value = "/user-model", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getAllUsers(@RequestBody UserModel userModel) {
        return ResponseEntity.status(HttpStatus.OK).body(userModel);
    }

    @GetMapping("/user")
    public ResponseEntity<String> getUser(Principal principal) {
//        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
//        String userName = (String) token.getTokenAttributes().get("name");
//        String userEmail = (String) token.getTokenAttributes().get("email");
//        return ResponseEntity.ok("Hello User \nUser Name : " + userName + "\nUser Email : " + userEmail);
        return ResponseEntity.status(HttpStatus.OK).body("Hello user");
    }
}
