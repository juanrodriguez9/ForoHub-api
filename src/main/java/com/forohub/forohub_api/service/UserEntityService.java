package com.forohub.forohub_api.service;

import com.forohub.forohub_api.domain.profile.IProfileRepository;
import com.forohub.forohub_api.domain.user.DataRegisterUser;
import com.forohub.forohub_api.domain.user.DataUserList;
import com.forohub.forohub_api.domain.user.IUserRepository;
import com.forohub.forohub_api.domain.user.UserEntity;
import com.forohub.forohub_api.infra.errors.IntegrityValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService  {

    private IUserRepository userRepository;
    private IProfileRepository profileRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserEntityService(IUserRepository userRepository, IProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Boolean registerUser(DataRegisterUser dataRegisterUser) {
        return true;
    }//pendiente corregir

    public Page<DataUserList> listUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(DataUserList::new);
    }

    public void deleteUser(Long id) {
    }

    public UserEntity updateUser(@Valid Long id, @Valid DataRegisterUser dataRegisterUser) {
        if(userIsEnabledAndExist(id)){
            throw new IntegrityValidation("El usuario a actualizar no existe ");
        }

        //validar que el correo no exista
        if(userRepository.existsByEmail(dataRegisterUser.email()).booleanValue()){
            throw new IntegrityValidation("El correo ya existe...");
        }

        UserEntity userEntity = userRepository.getReferenceById(id);
        userEntity.updateUser(
                dataRegisterUser.name(),
                dataRegisterUser.email(),
                passwordEncoder.encode(dataRegisterUser.password())
        );

        return userEntity;
    }

    private boolean userIsEnabledAndExist(Long id){
        var userExist = userRepository.findById(id);

        //validar que exista el id o este habilitado
        return (!userExist.isPresent() || !userExist.get().isEnabled());
    }

}
