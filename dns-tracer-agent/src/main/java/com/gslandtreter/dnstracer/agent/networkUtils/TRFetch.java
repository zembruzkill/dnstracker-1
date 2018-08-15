package com.gslandtreter.dnstracer.agent.networkUtils;

import com.gslandtreter.RawTraceRoute;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TRFetch {

    private static final String IPADDRESS_PATTERN =
            "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

    private static final Pattern ipPattern = Pattern.compile(IPADDRESS_PATTERN);

    private static List<String> parseOutput(String output) {
        List<String> hops = new ArrayList<>();

        String lines[] = output.split("\\r?\\n");

        for (String line : lines) {
            Matcher ipMatcher = ipPattern.matcher(line);
            if (ipMatcher.find()) {
                hops.add(ipMatcher.group());
            } else if (line.contains("*")) {
                hops.add("*");
            }
        }

        return hops;
    }

    public static List<InetAddress> getHopsTo(String domainName) {

        try {
            RawTraceRoute traceRoute = new RawTraceRoute((int) Thread.currentThread().getId(), 1000);
            return traceRoute.execute(InetAddress.getByName(domainName), 35);
        } catch (IOException e) {
            return null;
        }
    }
}
