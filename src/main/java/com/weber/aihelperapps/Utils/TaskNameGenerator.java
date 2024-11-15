package com.weber.aihelperapps.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskNameGenerator {
    // Map действий к инфинитивам
    private static final Map<String, String> ACTIONS_MAP = new HashMap<>() {{
        put("напишу", "написать");
        put("сделаю", "сделать");
        put("отправлю", "отправить");
        put("проведу", "провести");
        put("решу", "решить");
        put("подготовлю", "подготовить");
        put("создам", "создать");
        put("проверю", "проверить");
        put("выполню", "выполнить");
        put("позвоню", "позвонить");
    }};

    // Ключевые слова, с которыми будем строить задачу
    private static final String[] TASK_KEYWORDS = {
            "отчёт", "код", "встречу", "документ", "презентацию", "задачу", "письмо", "проект"
    };

    // Паттерн для поиска слов из ACTIONS_MAP
    private static final Pattern ACTION_PATTERN = Pattern.compile(String.join("|", ACTIONS_MAP.keySet()), Pattern.CASE_INSENSITIVE);

    public static String generateTaskName(String message) {
        // Приводим сообщение к нижнему регистру
        String lowerCaseMessage = message.toLowerCase().trim();

        // Ищем первое подходящее действие
        Matcher actionMatcher = ACTION_PATTERN.matcher(lowerCaseMessage);
        if (actionMatcher.find()) {
            String action = actionMatcher.group().trim();
            String actionInfinitive = ACTIONS_MAP.get(action);  // Получаем инфинитив для найденного действия

            // Ищем ключевое слово (например, отчёт, код)
            for (String keyword : TASK_KEYWORDS) {
                if (lowerCaseMessage.contains(keyword)) {
                    // Формируем имя задачи
                    return capitalize(actionInfinitive) + " " + keyword;
                }
            }
        }

        // Если не нашли подходящих слов, возвращаем сообщение как есть
        return message;
    }

    private static String capitalize(String action) {
        // Приводим первую букву инфинитива в верхний регистр
        return action.substring(0, 1).toUpperCase() + action.substring(1);
    }
}
