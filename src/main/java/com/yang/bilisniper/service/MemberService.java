package com.yang.bilisniper.service;

import javax.validation.constraints.NotNull;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
public interface MemberService {

    boolean getMemberInfo(@NotNull final String url, final Long mid) throws Exception;

    void saveMemberInfo(Long mid);
}
