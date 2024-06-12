package com.martinm.baconator.controller;

import com.martinm.baconator.service.BaconService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/give-me-bacon")
public class BaconController {

  private final BaconService baconService = new BaconService();

  @GetMapping("/{howmuch}")
  public Map<String, Object> getBacon(@PathVariable int howmuch) {
    return baconService.getBaconData(howmuch);
  }
}