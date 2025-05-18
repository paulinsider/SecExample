package com.example.controller;

import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("accuracy/context_sensitive/argument_return_value_passing/argument_value_passing")
public class Expression_MethodInvocation_001_T {
    
    @GetMapping("Expression_MethodInvocation_001_T/{cmd}")
    public Map<String, Object> aTaintCase013(@PathVariable String cmd) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            // 执行命令
            Process process = Runtime.getRuntime().exec(cmd);
            
            // 读取命令输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            
            // 等待命令执行完成
            int exitCode = process.waitFor();
            
            // 设置返回结果
            result.put("success", true);
            result.put("output", output.toString());
            result.put("exitCode", exitCode);
            
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return result;
    }
} 