package com.scalar.splitwise.Controllers;

import org.springframework.stereotype.Controller;

import com.scalar.splitwise.DTOs.SettleUpUserRequestDTO;
import com.scalar.splitwise.DTOs.SettleUpuserResponseDTO;
import com.scalar.splitwise.Services.SettleUpService;

@Controller
public class SettleUpController {

    private SettleUpService settleUpService;

    public SettleUpuserResponseDTO settleUpUser(SettleUpUserRequestDTO request){
        SettleUpuserResponseDTO response = new SettleUpuserResponseDTO();
        response.setTransactions(settleUpService.settleUpUser(request.getUserId()));
        return response;
    }
    public void settleUpGroup(){

    }
}
