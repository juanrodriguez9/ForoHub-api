package com.forohub.forohub_api.controller;

import com.forohub.forohub_api.domain.user.DataRegisterUser;
import com.forohub.forohub_api.domain.user.DataUserList;
import com.forohub.forohub_api.service.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserEntityController {

    @Autowired
    private UserEntityService userService;

    @GetMapping
    public ResponseEntity<Page<DataUserList>> allUsers(@PageableDefault(size = 4, sort = "name") Pageable pageable){
        return ResponseEntity.ok(userService.listUsers(pageable));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("El usuario se ha eliminado");
    }

    @PutMapping("/{id}")
    @jakarta.transaction.Transactional
    public ResponseEntity userUpdate(@PathVariable @Valid Long id, @RequestBody @Valid DataRegisterUser dataRegisterUser){
        var userResponse = userService.updateUser(id, dataRegisterUser);

        return ResponseEntity.ok(userResponse);
    }

}
