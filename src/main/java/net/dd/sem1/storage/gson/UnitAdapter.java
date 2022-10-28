package net.dd.sem1.storage.gson;

import com.google.gson.*;
import net.dd.sem1.Main;
import net.dd.sem1.figure.Circle;
import net.dd.sem1.figure.FourCornersFigure;
import net.dd.sem1.gui.render.RenderElement;
import net.dd.sem1.gui.render.Unit;

import java.lang.reflect.Type;

public class UnitAdapter implements JsonDeserializer<Unit> {
  @Override
  public Unit deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject jsonObject = json.getAsJsonObject();
    String type = jsonObject.get("type").getAsString();

    switch (type) {
      case "render_element":
        return context.deserialize(jsonObject, RenderElement.class);
      case "circle":
        return context.deserialize(jsonObject, Circle.class);
      case "four_corners":
        return context.deserialize(jsonObject, FourCornersFigure.class);
      default:
        return null;
    }
  }
}
