package etf.unibl.org.ip.ipbackend.services.implementation;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import etf.unibl.org.ip.ipbackend.models.dtos.FitFact;
import etf.unibl.org.ip.ipbackend.services.FitFactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FitFactsServiceImpl implements FitFactsService {

    @Value("${fit-facts-url}")
    private String fitFactsUrl;

    @Override
    public List<FitFact> getAllFitFacts() {
        List<FitFact> fitFacts = new ArrayList<>();
        try {
            URL url = new URL(fitFactsUrl);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(url));
            List<SyndEntry> entries = feed.getEntries();
            for (SyndEntry entry : entries)
            {
                fitFacts.add(new FitFact(entry.getCategories().get(0).getName(), entry.getTitle(), entry.getDescription().getValue(), entry.getLink()));
            }
        } catch (FeedException | IOException e) {
            throw new RuntimeException(e);
        }
        return fitFacts;
    }
}
