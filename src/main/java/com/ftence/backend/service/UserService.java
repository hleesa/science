package com.ftence.backend.service;

import com.ftence.backend.dto.Response.LoginResponseDTO;
import com.ftence.backend.entity.Comment;
import com.ftence.backend.entity.User;
import com.ftence.backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<LoginResponseDTO> getUserInfo(String intraId){
        return userRepository.findByIntraId(intraId)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private LoginResponseDTO convertEntityToDto(User user) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO = modelMapper.map(user, LoginResponseDTO.class);

        return loginResponseDTO;
    }

   /* private User convertDtoToEntity(UserCommentDTO userCommentDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        User user = new User();
        user = modelMapper.map(userCommentDTO, User.class);
        return user;
    }*/
}
