package com.sigp.test.demo.services;

import com.sigp.test.demo.model.UserData;
import com.sigp.test.demo.model.UsersDataPage;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final WebClient webClient = WebClient.create("https://jsonmock.hackerrank.com/api/article_users");

    @Override
    public List<String> getUsernames(int threshold) {
        UsersDataPage firstUserDataPage = getUsersDataPage(1);
        final int total_pages = firstUserDataPage.getTotal_pages();

        List<UserData> userDataList = filterUserDataList(firstUserDataPage.getData(), threshold);
        for(int i = 2; i <= total_pages; i++){
            userDataList.addAll(filterUserDataList(getUsersDataPage(i).getData(), threshold));
        }

        return  userDataList.stream().map(UserData::getUsername).collect(Collectors.toList());
    }

    private UsersDataPage getUsersDataPage(int numOfPage){
        Mono<UsersDataPage> firstUsersDataPageMono =  webClient.get()
                .uri("/search?page=" + numOfPage)
                .retrieve()
                .bodyToMono(UsersDataPage.class);

        return firstUsersDataPageMono.block();
    }

    private List<UserData> filterUserDataList(List<UserData> userDataList, int threshold){
        return userDataList.stream()
                .filter(userData -> userData.getSubmission_count() > threshold)
                .collect(Collectors.toList());
    }
}
