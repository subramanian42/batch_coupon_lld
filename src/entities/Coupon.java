package entities;

public class Coupon {
    private String code;
    private CouponType couponType;

    public Coupon(String code, CouponType couponType) {
        this.code = code;
        this.couponType = couponType;
    }

    public String getCode() {
        return code;
    }

    public CouponType getCouponType() {
        return couponType;
    }
}
