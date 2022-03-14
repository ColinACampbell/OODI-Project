package com.hexagrammers.DamPlay.Controllers;

import com.hexagrammers.DamPlay.Models.Http.AuthResponse;
import com.hexagrammers.DamPlay.Models.Http.HttpResponseBody;
import com.hexagrammers.DamPlay.Models.PrincipalUserDetails;
import com.hexagrammers.DamPlay.Models.User;
import com.hexagrammers.DamPlay.Services.UserManager;
import com.hexagrammers.DamPlay.Utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserManager userManager;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody User user)
    {

        if (userManager.findByEmail(user.getEmail()) != null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword())); // Encrypt the user password on creation
        userManager.createUser(user);

        return new ResponseEntity<>(new Object(),HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User userBody)
    {

        User user = userManager.findByEmail(userBody.getEmail());
        if (user == null)
        {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            if (new BCryptPasswordEncoder().matches(userBody.getPassword(), user.getPassword()))
            {
                // Deal with manual authentication
                PrincipalUserDetails principalUserDetails = new PrincipalUserDetails(user);
                UsernamePasswordAuthenticationToken authReq
                        = new UsernamePasswordAuthenticationToken(principalUserDetails,userBody.getPassword());
                Authentication auth = authenticationManager.authenticate(authReq); // authenticate the user by passing the token into the authentication layer

                JWTUtil jwtUtil = new JWTUtil();
                String token = jwtUtil.generateToken(principalUserDetails);

                // Also return the user info as well as the members
                return new ResponseEntity<>(new AuthResponse(user,userManager.findAllUsers(),token),HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}
