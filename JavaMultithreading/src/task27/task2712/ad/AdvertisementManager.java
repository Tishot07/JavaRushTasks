package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {

    public final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        if (storage.list().isEmpty())
            throw new NoVideoAvailableException();
        else {
            List<Advertisement> list = selectVideo();
            Collections.sort(list, new Comparator<Advertisement>() {
                @Override
                public int compare(Advertisement o1, Advertisement o2) {
                    return 0;
                }
            });

            long amount = 0;
            int totalDuration = 0;
            for (Advertisement ad : list) {
                totalDuration += ad.getDuration();
                amount += ad.getAmountPerOneDisplaying();
            }
            StatisticManager.getInstance().register(new VideoSelectedEventDataRow(list, amount, totalDuration));

            for (Advertisement ad:
                    list) {
                ConsoleHelper.writeMessage(ad.getName() + " is displaying... " +
                        ad.getAmountPerOneDisplaying() + ", " +
                        1000 * ad.getAmountPerOneDisplaying() / ad.getDuration());
                ad.revalidate();
            }
        }
    }

    private List<Advertisement> selectVideo() {
        List<Advertisement> sortList = new ArrayList<>();
        sortList = storage.list();

        Collections.sort(sortList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                long dif = o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying();
                if (dif == 0)
                    dif = o2.getDuration() - o1.getDuration();
                return (int)dif;
            }
        });

        List<Advertisement> result = new ArrayList<>();
        int time = timeSeconds;
        for (Advertisement list:
                sortList) {
            if (list.getHits() != 0 && list.getDuration() <= time) {
                result.add(list);
                time -= list.getDuration();
            }
        }

        return result;
    }


}
