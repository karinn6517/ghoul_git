package karin.slot.ghoul.config;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

import karin.slot.ghoul.dto.GhoulForm;

public class DatastoreTest {
  @Test
  void test() {
    System.out.println("Datastore test");
    Datastore datastore = DatastoreOptions.newBuilder()
        .setProjectId("ghoul-475802") // ← ここに明示的に指定
        .build()
        .getService();

    // --- サンプル: GhoulForm を UUID をキーにして保存 ---
    String id = UUID.randomUUID().toString();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("GhoulForm");
    Key key = keyFactory.newKey(id);

    // GhoulForm インスタンスを作成
    GhoulForm form = new GhoulForm();
    form.setCount1(1);
    form.setTotal1(10);
    form.setCount2(2);
    form.setTotal2(20);
    form.setCount3(3);
    form.setTotal3(30);
    form.setCount4(4);
    form.setTotal4(40);
    form.setKk(7);
    form.setToggleFlag(true);

    // Entity に変換して保存
    Entity entity = Entity.newBuilder(key)
        .set("count1", form.getCount1())
        .set("total1", form.getTotal1())
        .set("count2", form.getCount2())
        .set("total2", form.getTotal2())
        .set("count3", form.getCount3())
        .set("total3", form.getTotal3())
        .set("count4", form.getCount4())
        .set("total4", form.getTotal4())
        .set("kk", form.getKk())
        .set("toggleFlag", form.isToggleFlag())
        .build();

    datastore.put(entity);
    System.out.println("Saved GhoulForm with id=" + id);

    // --- サンプル: 取得 ---
    Entity got = datastore.get(key);
    if (got == null) {
      System.out.println("Entity not found for id=" + id);
      return;
    }

    // Entity から GhoulForm へ復元
    GhoulForm loaded = new GhoulForm();
    loaded.setCount1((int) got.getLong("count1"));
    loaded.setTotal1((int) got.getLong("total1"));
    loaded.setCount2((int) got.getLong("count2"));
    loaded.setTotal2((int) got.getLong("total2"));
    loaded.setCount3((int) got.getLong("count3"));
    loaded.setTotal3((int) got.getLong("total3"));
    loaded.setCount4((int) got.getLong("count4"));
    loaded.setTotal4((int) got.getLong("total4"));
    loaded.setKk((int) got.getLong("kk"));
    loaded.setToggleFlag(got.getBoolean("toggleFlag"));

    System.out.println("Loaded GhoulForm: count1=" + loaded.getCount1() + " total1=" + loaded.getTotal1()
        + " toggle=" + loaded.isToggleFlag());
  }
}
