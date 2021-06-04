# Item-API
Easily make yourself some inventories for players to interact in or just simply view. This API allows you to make inventory creation a lot easier by not having to create a new inventory every time you are in need of creating on. This also covers a lot of annoying edge-cases you have to do every time you work on inventory logics.

We also add an ItemBuilder util to make your life a lot easier, for example using an [Inventory API](https://github.com/RevityDevelopment/Menu-API "MenuAPI Link") updating items.
# Download
To setup Item-API usage with maven, put the following in your pom.xml

```xml
<repositories>
    <!-- Add the Item-API repository -->
    <repository>
        <id>item-api-repo</id>
        <url>https://raw.githubusercontent.com/jaymesss/Item-API/repository/</url>
    </repository>
</repositories>

<dependencies>
    <!-- Depend on & shade in the Item-API -->
    <dependency>
        <groupId>cc.revity</groupId>
        <artifactId>item-api</artifactId>
        <version>1.0.0</version>
        <scope>compile</scope>
    </dependency>
</dependencies>

```

# Code Example

```java
public class ExampleItem extends ClickableItem {
    @Override
    public String getId() {
        return "Example";
    }

    @Override
    public ItemStack getItemStack() {
        return ItemBuilder.from(Material.REDSTONE).name("&bExample").addLore("&aLine one!", "&eLine two!", "&cLine three!").build();
    }

    @Override
    public boolean onClick(Player player, ItemStack clicked, Action action) {
        if (action != Action.RIGHT_CLICK_AIR) {
            player.sendMessage(ChatColor.RED + "You can only right click this item!");
            return false;
        }
        player.sendMessage(ChatColor.GREEN + "Diamonds were granted to you!");
        player.getInventory().addItem(ItemBuilder.from(Material.DIAMOND).amount(16).name("&bDiamonds!").build());
        this.takeOne(player);
        return false;
    }
}
```

```java
public class ItemAPIPlugin extends JavaPlugin {

    private ExampleItem exampleItem;

    public void onEnable() {
        ItemAPI.register(this);
        ItemAPI.registerItem(exampleItem = new ExampleItem());
    }

    public void onDisable() {
        ItemAPI.unregisterItem(exampleItem);
    }
}

```

# Compilation
Compilation requires the following to be fulfilled:
* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html "Java 8 Link")
* [Maven 3](http://maven.apache.org/download.html "Maven 3 Link")

# Updates
This plugin is provided "as is", which means no updates or new features are guaranteed. We will do our best to keep updating and pushing new updates, and you are more than welcome to contribute your time as well and make pull requests for bug fixes.

Once these tasks have been taken care of, compilation via `mvn clean install` will result in `target/item-api-1.0.0.jar` being created.

# License
This software is available under the following licenses:
* GNU General Public License (GPL) version 3

