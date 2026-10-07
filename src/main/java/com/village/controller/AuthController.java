package com.village.controller;
import com.village.service.UserService; import org.springframework.http.*; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*; import java.util.Map;
@RestController @RequestMapping("/api/auth") public class AuthController{
 private final UserService service; public AuthController(UserService s){service=s;}
 @GetMapping("/me") public Map<String,Object> me(Authentication a){return service.safe(service.byUsername(a.getName()));}
 @PostMapping("/register") @ResponseStatus(HttpStatus.CREATED) public Map<String,Object> register(@RequestBody Map<String,String> in){return service.safe(service.register(in));}
}
