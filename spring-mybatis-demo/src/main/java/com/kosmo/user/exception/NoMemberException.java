package com.kosmo.user.exception;

public class NoMemberException extends Exception{

	public NoMemberException() {
		super("회원이 아닙니다.");
	}
	
	public NoMemberException(String msg) {
		super(msg);
	}

}
