package com.scalar.splitwise.Commands;

import java.util.List;

import org.springframework.stereotype.Component;

import com.scalar.splitwise.Controllers.SettleUpController;
import com.scalar.splitwise.DTOs.SettleUpUserRequestDTO;
import com.scalar.splitwise.DTOs.SettleUpuserResponseDTO;

@Component
public class SettleUpCommand implements Command {

    private SettleUpController settleUpController;

    @Override
    public void execute(String input) {

        // u1 settleUpUser
        List<String> words = List.of(input.split(" "));

        Long userId = Long.valueOf(words.get(0));
        SettleUpUserRequestDTO request = new SettleUpUserRequestDTO();
        request.setUserId(userId);

        SettleUpuserResponseDTO response = settleUpController.settleUpUser(request);
    }

    @Override
    public boolean matches(String input) {

        List<String> words = List.of(input.split(" "));

        return words.size() == 2 && words.get(1).equalsIgnoreCase(CommandKeywords.settleUp);
    }

}
