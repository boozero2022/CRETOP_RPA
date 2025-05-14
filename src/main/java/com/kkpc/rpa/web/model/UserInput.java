package com.kkpc.rpa.web.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserInput {
    private List<String> businessNumbers;
    private List<String> emailAddresses;
    private boolean includesSummary;

    @Override
    public String toString() {
        return "UserInput{" +
                "businessNumbers=" + businessNumbers +
                ", emailAddresses=" + emailAddresses +
                ", includesSummary=" + includesSummary +
                '}';
    }
}
