package com.example.demo.resultvm;

import com.google.gson.annotations.Expose;

import lombok.Data;

/**
 * <pre>
 * 화면 전달용 용 공통 결과 랩핑용 클래스
 * </pre>
 */
@Data
public class ResultMsg
{

    /**
     * 결과 코드
     */
    @Expose
    private String resultCd;

    /**
     * 성공/실패 여부(Boolean)
     */
    @Expose
    private boolean bResult = false;

    /**
     * 결과 메시지
     */
    @Expose
    private String resultMsg;

    /**
     * 결과 컨텐츠
     *
     * @Expose
     *         private Object contents;
     *         public ResultMsgVM ()
     *         {
     *         };
     */

    public ResultMsg ( final EnumResultCode2 enumResultCode )
    {
        this.bResult = enumResultCode.isSuccess ();
        this.resultCd = enumResultCode.isResultCd ();
        this.resultMsg = enumResultCode.getMessage ();
    }

    public void setResultEnum ( final EnumResultCode2 enumResultCode )
    {
        this.bResult = enumResultCode.isSuccess ();
        this.resultCd = enumResultCode.isResultCd ();
        this.resultMsg = enumResultCode.getMessage ();
    }

}
