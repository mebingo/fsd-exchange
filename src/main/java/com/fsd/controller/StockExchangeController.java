package com.fsd.controller;


import com.fsd.entity.StockExchangeEntity;
import com.fsd.service.ExchangeService;
import com.fsd.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
// @RequestMapping("/admin/manage/exchange")
public class StockExchangeController {

	@Autowired
	private ExchangeService exchangeService;


	@PostMapping("/admin/manage/exchange")
	public CommonResult createExchange(@RequestBody StockExchangeEntity stcokExchange) {
		return exchangeService.save(stcokExchange);
	}

	@PutMapping("/admin/manage/exchange")
	public CommonResult updateExchange(@RequestBody StockExchangeEntity stcokExchange) {
		return exchangeService.updateStockExchange(stcokExchange);
	}

	@GetMapping("/search/exchange")
	public CommonResult searchExchange() {
		return exchangeService.findAll();
	}

	@GetMapping("/search/exchange/id/{id}")
	public CommonResult searchExchange(@PathVariable int id) {
		return exchangeService.findById(id);
	}
}
