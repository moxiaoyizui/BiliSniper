package com.yang.bilisniper.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
@Entity
public class FollowRelation {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long followingMid;

    @Column(nullable = false)
    private Long followerMid;
}
