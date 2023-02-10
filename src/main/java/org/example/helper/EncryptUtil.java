package org.example.helper;

public class EncryptUtil {

    public static boolean _checkParam(String userNm, String userPW) throws Exception {
        try{
            if (userNm == null
                    || userNm.trim().length() == 0) {
                throw new Exception("Param [UserNM] is empty");
            }

            if (userPW == null
                    || userPW.trim().length() == 0) {
                throw new Exception("Param [UserPW] is empty");
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    // 암호화 / 복호화
    // 텍스트 -> 해쉬코드 작업
    // 사용자 이메일, 이름, 전화번호 : 특정 사용자를 지칭할 수 있는 정보는 암호화
    // AES256, SHA
    // 실무에서는 EncrytUtil 클래스를 만들어서 공동 활용
    //   _checkPW();
    // 유효성 확인???
    // 사용자가?? 내가해줘??


    //        if (!EncryptUtil.checkUserPW(this.userPW)){
//            // 에러 로그 기록
//            throw new Exception("패쓰워드 이상해");
//        }
//    }
    public static boolean checkUserPW(String pw){

        try {

        }catch(Exception e){

        }

        return true;
    }
}
