package org.example.standard.utils;

import lombok.Setter;
import lombok.SneakyThrows;
import org.example.domain.quotation.quotation.datas.Quotation;
import org.example.global.OutputAndSwitch;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class util {
    @Setter
    public static Scanner sc = new Scanner(System.in);

    public static String scanner() {
        return sc.nextLine();
    }

    public static int getKeyValue(Map<String, String> opt, String key) {
        return Integer.parseInt(opt.get(key));
    }

    public static int quoteToIndex(List<Quotation> quotations, int num) {
        for (int i = 0; i < quotations.size(); i++) {
            if (quotations.get(i).getId() == num) {
                return i;
            }
        }
        return -1;
    }

    public static String printAndScan(String print) {
        System.out.print(print); // 명언 입력
        return scanner();
    }


    @SneakyThrows
    public static void fileSave(String filepath, List<Quotation> quotations) {
        String basicpath = filepath;
        quotations.stream()
                .map(quotation -> quotation.getId() + "," + quotation.getId() + "/" + quotation.getContent() + "/" + quotation.getAuthor())
                .forEach(line -> {
                    try {
                        String[] arr = line.split(",");
                        Files.write(Paths.get(basicpath + arr[0] + ".txt"), arr[1].getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                    } catch (IOException e) {
                        String[] arr = line.split(",");
                        // 부모 디렉토리가 없어서 발생한 예외인지 확인합니다.
                        Path parentDir = Paths.get(basicpath + arr[0] + ".txt").getParent();
                        if (parentDir != null && Files.notExists(parentDir)) {
                            try {
                                Files.createDirectories(parentDir);
                                // 디렉토리를 생성한 후 다시 시도합니다.
                                Files.write(Paths.get(basicpath + arr[0] + ".txt"), arr[1].getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            // 다른 종류의 IOException 그대로 예외를 던집니다.
                            e.printStackTrace();
                        }
                    }

                });
    }

    public static void fileLoad(OutputAndSwitch outputAndSwitch) {

    }

    @SneakyThrows
    public static void fileDelete(String filePath) {
        // Files.walk를 사용하여 모든 하위 파일 및 디렉토리를 찾음
        try (Stream<Path> paths = Files.walk(Paths.get(filePath))) {
            paths.sorted((p1, p2) -> -p1.compareTo(p2))
                    .forEach(path -> {
                        try {
                            Files.delete(path); // 파일 또는 디렉토리 삭제
                            System.out.println("삭제: " + path);
                        } catch (NoSuchFileException e) {
                            // 이미 삭제된 경우 무시
                        } catch (IOException e) {
                            System.err.println("삭제 실패: " + path);
                            e.printStackTrace();
                        }
                    });
        }
    }
}
