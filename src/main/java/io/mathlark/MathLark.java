package io.mathlark;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;

import jline.TerminalFactory;
import jline.console.ConsoleReader;
import jline.console.completer.StringsCompleter;

import io.mathlark.parser.LarkParserEngine;


public class MathLark {
    public static void main(String[] args) {
        console();
    }

    public static void console() {
        try {
            LarkParserEngine engine = new LarkParserEngine();
            try (ConsoleReader console = new ConsoleReader()) {
                List<String> strList = new ArrayList<>();

                InputStream stream = MathLark.class.getClassLoader().getResourceAsStream("functions.lst");
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                String fct;
                while ((fct = br.readLine()) != null) {
                    strList.add(fct);
                }
                
                console.addCompleter(new StringsCompleter(strList));
                console.setPrompt(">> ");

                String line;
                while ((line = console.readLine()) != null) {
                    if (line.equals("!x")) {
                        System.out.println("Session terminated");
                        return;
                    }

                    Object result = engine.parse(line);
                    if (result != null) {
                        System.out.println(result.toString());
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                TerminalFactory.get().restore();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
