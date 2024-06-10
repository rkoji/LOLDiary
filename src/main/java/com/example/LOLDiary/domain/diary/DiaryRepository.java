package com.example.LOLDiary.domain.diary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary,Long> {

    Optional<Diary> findByName(String nickname);

    Optional<Diary> findByNameAndKda(String nickname,float kda);

    Optional<Diary> findByKda(float kda);
}
