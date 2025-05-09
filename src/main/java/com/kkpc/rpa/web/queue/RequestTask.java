package com.kkpc.rpa.web.queue;

import com.kkpc.rpa.web.model.UserInput;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestTask {

    private UserInput userInput;
    private int retryCount;

    public RequestTask(UserInput userInput) {
        this.userInput = userInput;
        this.retryCount = 0;
    }

    public RequestTask incrementRetry() {
        return new RequestTask(this.userInput, this.retryCount + 1);
    }

    @Override
    public String toString() {
        return "RequestTask{" +
                "userInput=" + userInput +
                ", retryCount=" + retryCount +
                '}';
    }
}
