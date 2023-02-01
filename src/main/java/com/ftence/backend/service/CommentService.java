package com.ftence.backend.service;

import com.ftence.backend.dto.Request.CommentRequestDTO;
import com.ftence.backend.dto.Response.CommentResponseDTO;
import com.ftence.backend.dto.Response.LoginResponseDTO;
import com.ftence.backend.entity.Comment;
import com.ftence.backend.entity.Rating;
import com.ftence.backend.entity.Subject;
import com.ftence.backend.entity.User;
import com.ftence.backend.repository.CommentRepository;
import com.ftence.backend.repository.RatingRepository;
import com.ftence.backend.repository.SubjectRepository;
import com.ftence.backend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Destination;
import java.util.List;
import java.util.RandomAccess;
import java.util.stream.Collectors;

//@RequiredArgsConstructor
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ModelMapper modelMapper;

    public void write(CommentRequestDTO commentRequestDTO) {

        Rating rating = convertCommentRequestDtoToRatingEntity(commentRequestDTO);
        System.out.println(rating.toString());
        ratingRepository.save(rating);

        Comment comment = convertCommentRequestDtoToCommentEntity(commentRequestDTO, rating);
        System.out.println(comment.toString());
        commentRepository.save(comment);

    }

    public List<LoginResponseDTO> getAllUserComment() {
        return commentRepository.findAll()
                .stream()
                .map(this::convertEntityToLoginResponseDto)
                .collect(Collectors.toList());
    }

    public List<CommentResponseDTO> getAllSubjectComment(Subject subject) {

        List<CommentResponseDTO> list = commentRepository.findBySubject(subject)
                .stream()
                .map(this::convertEntityToCommentResponseDto)
                .collect(Collectors.toList());
        return list;
    }


    private LoginResponseDTO convertEntityToLoginResponseDto(Comment comment) {
        return modelMapper.map(comment, LoginResponseDTO.class);
    }

    public CommentResponseDTO convertEntityToCommentResponseDto(Comment comment) {

        User user = comment.getUser();
        Subject subject = comment.getSubject();
        Rating rating = comment.getRating();

        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setIntraId(user.getIntraId());
        commentResponseDTO.setUserLevel(user.getLevel());
        commentResponseDTO.setSubjectName(subject.getName());
        commentResponseDTO.setCommentId(comment.getId());
        commentResponseDTO.setContent(comment.getContent());
        commentResponseDTO.setUpdateTime(comment.getUpdateTime());
        commentResponseDTO.setRating(rating.getStarRating());
        commentResponseDTO.setTimeTaken(rating.getTimeTaken());
        commentResponseDTO.setAmountStudy(rating.getAmountStudy());
        commentResponseDTO.setBonus(rating.getBonus());
        commentResponseDTO.setDifficulty(rating.getDifficulty());

        return commentResponseDTO;
    }

    private Rating convertCommentRequestDtoToRatingEntity(CommentRequestDTO commentRequestDTO) {

        Rating rating = new Rating();

        rating.setStarRating(commentRequestDTO.getStarRating());
        rating.setTimeTaken(commentRequestDTO.getTimeTaken());
        rating.setAmountStudy(commentRequestDTO.getAmountStudy());
        rating.setBonus(commentRequestDTO.getBonus());
        rating.setDifficulty(commentRequestDTO.getDifficulty());

        return rating;
    }

    private Comment convertCommentRequestDtoToCommentEntity(CommentRequestDTO commentRequestDTO, Rating rating) {

        System.out.println(commentRequestDTO.getIntraId());

        User user = userRepository.findByIntraId(commentRequestDTO.getIntraId()).get(0);

        System.out.println(user.toString());

        Subject subject = subjectRepository.findByName(commentRequestDTO.getSubjectName());
        // CommentRequestDto 를 Comment랑 Rating 으로 나눠서 저장해야 함.

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setSubject(subject);
        comment.setRating(rating);
        comment.setContent(commentRequestDTO.getContent());
        comment.setUserLevel(user.getLevel());

        return comment;
    }
}
