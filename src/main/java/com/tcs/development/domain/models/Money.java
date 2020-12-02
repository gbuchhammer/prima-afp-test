package com.tcs.development.domain.models;

import java.io.Serializable;
import java.math.BigDecimal;

import com.tcs.development.domain.Currency;

import lombok.Getter;
import lombok.Setter;

//@Entity
//@Table(name = "money")
@Getter
@Setter
public class Money implements Serializable {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "money_id")
	private Long moneyId;
	
    private BigDecimal amount;
	
//	@Transient
    private Currency currency;

	private static final long serialVersionUID = 4661423604761557998L;

}
