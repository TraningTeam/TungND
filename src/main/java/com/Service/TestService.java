package com.Service;

import com.Model.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TestService {

    public int calculator(int number1, int number2) {
        return number1 + number2;
    }
}
