package com.example;

import org.apache.commons.lang3.StringUtils;

public class Main {

    public static void main(String[] args) {

        String jvmVersion = System.getProperty("java.version");
        String jvmVendor  = System.getProperty("java.vm.vendor");
        System.out.println("JVM: " + jvmVersion + " (" + jvmVendor + ")");

        String osName = System.getProperty("os.name");
        String osArch = System.getProperty("os.arch");
        System.out.println("OS: " + osName + " (" + osArch + ")");

        String reversed = StringUtils.reverse("Java");
        System.out.println("Reversed (Commons Lang): " + reversed);

        System.out.println("Привіт зі збірки!");
    }
}
