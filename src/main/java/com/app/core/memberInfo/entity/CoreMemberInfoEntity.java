package com.app.core.memberInfo.entity;

public class CoreMemberInfoEntity {
  private String memberId;
  private String memeberName;
  private String photo;
  private String account;
  private String password;
  private String isFrozen;

  public String getMemberId() {
    return memberId;
  }

  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }

  public String getMemeberName() {
    return memeberName;
  }

  public void setMemeberName(String memeberName) {
    this.memeberName = memeberName;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getIsFrozen() {
    return isFrozen;
  }

  public void setIsFrozen(String isFrozen) {
    this.isFrozen = isFrozen;
  }
}


