package lk.ijse.hostel.dto;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ReserveDetailDTO {
     private String resId;
     private String id;
     private String code;
     private int qty;
     private BigDecimal keyMoney;
     private String status;



}
