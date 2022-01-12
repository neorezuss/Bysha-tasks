package com.example.task4.service;

import com.example.task4.dto.ElixirDto;
import com.example.task4.entity.Elixir;
import com.example.task4.entity.User;
import com.example.task4.repository.ElixirRepository;
import com.example.task4.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElixirServiceImpl implements ElixirService {
    private static final String DEFAULT_SORTING = "name";

    private final ElixirRepository elixirRepository;
    private final UserRepository userRepository;

    public ElixirServiceImpl(ElixirRepository elixirRepository, UserRepository userRepository) {
        this.elixirRepository = elixirRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ElixirDto> getUserElixirs(String userEmail, String name, Integer costGT, Integer costLT, Integer level, String sortBy) {
        List<Elixir> filteredElixirs =
                elixirRepository.findElixirsByUsersEmailAndByNameAndByCostAndByLevel(userEmail, name, costGT, costLT, level, getSort(sortBy));

        return filteredElixirs.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean sellElixir(String userEmail, ElixirDto elixirDto) {
        User user = userRepository.getUserByEmail(userEmail);
        Elixir elixir = elixirRepository.getElixirByName(elixirDto.getName());

        boolean canSell = user.getElixirs().remove(elixir);
        if (canSell) {
            user.setCoins(user.getCoins() + elixir.getCost());
        }
        return canSell;
    }

    private ElixirDto convertToDto(Elixir elixir) {
        return new ElixirDto(elixir.getName(), elixir.getCost(), elixir.getLevel());
    }

    private Sort getSort(String sortString) {
        Sort sort;
        try {
            switch (sortString) {
                case "name":
                    sort = Sort.by("name");
                    break;
                case "cost":
                    sort = Sort.by("cost");
                    break;
                case "level":
                    sort = Sort.by("level");
                    break;
                case "name desc":
                    sort = Sort.by("name").descending();
                    break;
                case "cost desc":
                    sort = Sort.by("cost").descending();
                    break;
                case "level desc":
                    sort = Sort.by("level").descending();
                    break;
                default:
                    sort = Sort.by(DEFAULT_SORTING);
                    break;
            }
        } catch (NullPointerException e) {
            sort = Sort.by(DEFAULT_SORTING);
        }
        return sort;
    }
}
