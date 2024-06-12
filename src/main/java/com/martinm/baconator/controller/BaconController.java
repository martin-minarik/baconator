package com.martinm.baconator.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/give-me-bacon")
public class BaconController {

  @GetMapping("/{howmuch}")
  public Map<String, Object> getBacon(@PathVariable int howmuch) {
    Map<String, Object> response = new HashMap<>();

    response.put("howmuch", String.valueOf(howmuch));
    return response;
  }
}