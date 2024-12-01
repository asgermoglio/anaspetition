package com.germoglio.anaspetition.petition;




import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class PetitionService {

    private static int petitionCount = 0;
    private static List<Petition> petitions = new ArrayList<>();
    static {
        petitions.add(new Petition(++petitionCount, "ana", "Random name for petition", LocalDate.now().plusYears(1), false, ""));
        petitions.add(new Petition(++petitionCount, "ana", "CT5171 Petition", LocalDate.now().plusYears(2), false, ""));
        petitions.add(new Petition(++petitionCount, "ana", "University of Galway Petition", LocalDate.now().plusYears(3), false, ""));
    }

    public void addPetition(String username, String description, LocalDate targetDate, boolean done, String email){
        Petition petition = new Petition(++petitionCount, username, description,targetDate, done, email);
        petitions.add(petition);
    }

    public void deleteById(int id){

        Predicate<? super Petition> predicate = petition -> petition.getId() == id;
        petitions.removeIf(predicate);
    }

    public List<Petition> findByUsername(String username){

        return petitions;
    }

    public Petition findById(int id) {
        Predicate<? super Petition> predicate = petition -> petition.getId() == id;
        Petition petition = petitions.stream().filter(predicate).findFirst().get();
        return petition;
    }

    public void updatePetition(@Valid Petition petition) {
        deleteById(petition.getId());
        petitions.add(petition);
    }

}
