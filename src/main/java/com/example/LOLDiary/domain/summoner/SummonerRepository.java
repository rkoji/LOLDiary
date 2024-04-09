package com.example.LOLDiary.domain.summoner;

import com.example.LOLDiary.domain.summoner.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SummonerRepository extends JpaRepository<Summoner, Long> {

    Optional<Summoner> findByName(String nickname);

}
