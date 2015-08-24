package me.joogle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * A list of Google search results
 */
public class Results implements List<Result> {
    private ArrayList<Result> results;

    /**
     * Gets the desired search results from Google and parses them into JavaBeans
     * @param search
     */
    public Results(String search) {
        try {
            URL url = new URL("https://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=" + search.trim().replace(" ", "%20"));
            URLConnection connection = url.openConnection();
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();

            results = new ArrayList<>();
            JSONObject all = new JSONObject(builder.toString());
            JSONArray resultsArray = all.getJSONObject("responseData").getJSONArray("results");
            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject result = resultsArray.getJSONObject(i);
                Result res = new Result();
                res.fullUrl = new URL(result.getString("url"));
                res.shortUrl = result.getString("visibleUrl");
                res.title = result.getString("title");
                res.content = result.getString("content");
                res.json = result.toString();
                add(res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        return results.size();
    }

    @Override
    public boolean isEmpty() {
        return results.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return results.contains(o);
    }

    @Override
    public Iterator<Result> iterator() {
        return results.iterator();
    }

    @Override
    public Object[] toArray() {
        return results.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return results.toArray(a);
    }

    @Override
    public boolean add(Result result) {
        return results.add(result);
    }

    @Override
    public boolean remove(Object o) {
        return results.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return results.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Result> c) {
        return results.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Result> c) {
        return results.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return results.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return results.retainAll(c);
    }

    @Override
    public void clear() {
        results.clear();
    }

    @Override
    public Result get(int index) {
        return results.get(index);
    }

    @Override
    public Result set(int index, Result element) {
        return results.set(index, element);
    }

    @Override
    public void add(int index, Result element) {
        results.add(index, element);
    }

    @Override
    public Result remove(int index) {
        return results.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return results.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return results.lastIndexOf(o);
    }

    @Override
    public ListIterator<Result> listIterator() {
        return results.listIterator();
    }

    @Override
    public ListIterator<Result> listIterator(int index) {
        return results.listIterator(index);
    }

    @Override
    public List<Result> subList(int fromIndex, int toIndex) {
        return results.subList(fromIndex, toIndex);
    }
}
