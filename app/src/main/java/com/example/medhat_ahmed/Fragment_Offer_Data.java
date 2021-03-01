package com.example.medhat_ahmed;

public class Fragment_Offer_Data {

    private String urlImage;
    private String type;

    public Fragment_Offer_Data(String urlImage,String type)
    {
        this.urlImage = urlImage;
        this.type = type;
    }

    public Fragment_Offer_Data()
    {
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
