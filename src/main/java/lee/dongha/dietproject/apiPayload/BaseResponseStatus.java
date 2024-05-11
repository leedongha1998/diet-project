package lee.dongha.dietproject.apiPayload;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    SUCCESS(true, "COMMON200", "요청에 성공하였습니다."),
    REQUEST_ERROR(false, "COMMON401", "입력값을 확인해주세요."),
    INVALID_USER_JWT(false, "COMMON402","권한이 없는 유저의 접근입니다."),
    RESPONSE_ERROR(false, "COMMON404", "값을 불러오는데 실패하였습니다."),
    END_PAGE(false, "COMMON405", "마지막 페이지입니다."),

    //JWT
    EMPTY_JWT(false, "JWT4001", "JWT TOKEN 값이 존재하지 않습니다."),
    INVALID_JWT(false, "JWT4002", "유효하지 않은 JWT입니다."),
    FORBIDDEN(false, "JWT4003", "금지된 접근입니다."),
    // users
    USERS_EMPTY_USER_ID(false, "USER4001", "유저 아이디 값을 확인해주세요."),
    INVALID_USER_ID(false, "USER4002", "탈퇴한 유저입니다."),
    EXPIRED_JWT_ACCESS(false, "USER4003", "ACCESS TOKEN의 유효 기간이 만료되었습니다."),
    POST_USERS_EMPTY_EMAIL(false, "USER4004", "이메일을 입력해주세요."),
    POST_USERS_INVALID_EMAIL(false, "USER4005", "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false,"USER4006","중복된 이메일입니다."),
    POST_USERS_EXISTS_NICKNAME(false,"USER4007","중복된 닉네임입니다."),
    FAILED_TO_LOGIN(false,"USER4008","없는 아이디거나 비밀번호가 틀렸습니다."),
    FAILED_TO_AUTHENTICATION(false, "USER4009","올바른 인증이 아닙니다."),
    USER_NOT_FOUND(false, "USER4010","존재하지 않는 회원입니다."),
    USER_ALREADY_EXIST(false, "USER4011", "이미 존재하는 회원입니다."),

    //권한
    UNAUTHORIZED_USER(false, "PERMISSION4001", "로그인 후 이용 가능합니다."),
    ADMIN_PERMISSION_REQUIRED(false, "PERMISSION4002", "관리자 권한이 필요합니다."),
    HOST_PERMISSION_REQUIRED(false, "PERMISSION4003", "묘집사 권한이 필요합니다."),
    EXIST_DIET(false,"DIET4001" , "이미 존재하는 식단 일정입니다.(아침, 점심, 저녁은 하루에 1개씩)");

    private final boolean isSuccess;
    private final String code;
    private final String message;

    BaseResponseStatus(boolean isSuccess, String code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
