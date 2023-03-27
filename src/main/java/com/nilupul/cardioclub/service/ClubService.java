package com.nilupul.cardioclub.service;

import com.nilupul.cardioclub.dto.ClubDto;
import com.nilupul.cardioclub.model.Club;
import com.nilupul.cardioclub.repo.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubService {


    private final ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public List<ClubDto> findAllClubs(){
        List<Club> clubList = clubRepository.findAll();
        return clubList.stream().map(this::mapToClubDto).collect(Collectors.toList());
    }

    private ClubDto  mapToClubDto(Club club) {
        return ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();

    }

    public Club saveClub(Club club) {
      return clubRepository.save(club);
    }

    public ClubDto findById(Long cludId) {
       Club club = clubRepository.findById(cludId).get();
       return mapToClubDto(club);
    }

    public void updateClub(ClubDto updatedDto) {
        Club club = mapToClub(updatedDto);
        clubRepository.save(club);
    }

    private Club mapToClub(ClubDto updatedDto) {
        return Club.builder()
                .id(updatedDto.getId())
                .title(updatedDto.getTitle())
                .photoUrl(updatedDto.getPhotoUrl())
                .content(updatedDto.getContent())
                .build();

    }

   /* public ClubDto findClubById(Long id) {
        clubRepository.findById(id)
    } */
}
