package com.ftence.backend.service;

import com.ftence.backend.dto.Request.WikiRequestDTO;
import com.ftence.backend.dto.Response.WikiResponseDTO;
import com.ftence.backend.entity.Rating;
import com.ftence.backend.entity.Subject;
import com.ftence.backend.entity.User;
import com.ftence.backend.entity.Wiki;
import com.ftence.backend.repository.SubjectRepository;
import com.ftence.backend.repository.WikiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class WikiService {

    @Autowired
    private WikiRepository wikiRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    public WikiResponseDTO getSubjectWiki(String subjectName) {
        Subject subject = subjectRepository.findByName(subjectName);

        return convertEntityToWikiResponseDTO(subject);
    }

    private WikiResponseDTO convertEntityToWikiResponseDTO(Subject subject) {
        Long id = wikiRepository.findSubjectWikiId(subject.getId(), wikiRepository.findLastVersionOfSubject(subject.getId()));
        Wiki wiki = wikiRepository.findById(id).get();

        WikiResponseDTO wikiResponseDTO = new WikiResponseDTO();
        wikiResponseDTO.setBody(wiki.getBody());
        wikiResponseDTO.setVersion(wiki.getVersion());
        wikiResponseDTO.setEditor(wiki.getEditor());

        return wikiResponseDTO;
    }

    public void write(WikiRequestDTO wikiRequestDTO){
        // 저장을 하는데, 저장할 때 버전을 이전에서 +1 하고 저장
        Wiki wiki = convertWikiRequestDToToEntity(wikiRequestDTO);
        wikiRepository.save(wiki);

        // 저장하고 그 wiki id를 서브젝트 테이블에 저장
        wiki = wikiRepository.findById(wikiRepository.getMaxId()).get();
        Subject subject = subjectRepository.findByName(wikiRequestDTO.getSubjectName());
        subject.setWikiVersion(wiki.getVersion());
        subjectRepository.save(subject);
    }

    private Wiki convertWikiRequestDToToEntity(WikiRequestDTO wikiRequestDTO) {
        Subject subject = subjectRepository.findByName(wikiRequestDTO.getSubjectName());
        Wiki wiki = new Wiki();
        wiki.setSubject(subject);
        wiki.setBody(wikiRequestDTO.getBody());
        wiki.setEditor(wikiRequestDTO.getIntraId());
        wiki.setVersion(wikiRequestDTO.getVersion() + 1);

        return wiki;
    }

    /***
     * 버전이 같은 지 비교하는 함수
     */
    public boolean checkWikiVersion(WikiRequestDTO wikiRequestDTO) {

        Subject subject = subjectRepository.findByName(wikiRequestDTO.getSubjectName());

        if (wikiRequestDTO.getVersion() == 0)
            return true;

        if (subject.getWikiVersion() == wikiRequestDTO.getVersion())
            return true;
        else
            return false;
    }
}
