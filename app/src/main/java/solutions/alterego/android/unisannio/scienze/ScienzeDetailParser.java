package solutions.alterego.android.unisannio.scienze;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import solutions.alterego.android.unisannio.interfaces.Parser;

public class ScienzeDetailParser implements Parser<String> {
    ArrayList<String> bodyelements = new ArrayList<>();

    @Override
    public List<String> parse(Document document) {

        Elements newsBodys = document.select("div.item-page");

        for(Element element : newsBodys){
            String str = element.select("p").get(1).text();

            bodyelements.add(0,str);
        }

        return bodyelements;
    }
}
