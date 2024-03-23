package com.tez.dieticianpatientapp.dto.Request;

import com.tez.dieticianpatientapp.entities.Dietician;
import com.tez.dieticianpatientapp.entities.User;
import com.tez.dieticianpatientapp.utils.UserType;
import com.tez.dieticianpatientapp.validation.UniqueTckn;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DiteticianCreate(

        @Pattern(regexp = "\\d{11}", message = "Tc kimlik numarası 11 haneli olmalı")
        @UniqueTckn
        String tckn,
        @Size(min = 8, max=255)
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Şifreniz bir büyük bir küçük harf ve rakam içermeli")
        String password,
        String firstName,
        String lastName

) {
    public Dietician toDietician(){
            User user = new User();
            user.setPassword(password);
            user.setTckn(tckn);
            user.setUserType(UserType.Dietician);
            return new Dietician(firstName,lastName,user);
    }
}
