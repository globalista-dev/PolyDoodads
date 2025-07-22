![PolyDoodads](https://cdn.modrinth.com/data/cached_images/c1ca0918ddac605086acaba8297f20ab60d00f9b.png)

## Features
Polydoodads adds server-side trinkets that give specific buffs to the player. They are crafted from a number of materials and cut gems, each one with its own base attributs. 

Cut gems are created by right-clicking a grindstone with the selected gem in hand. E.g.: Emerald -> Cut Emerald. Cut Redstone and Cut Glowstone are made using Redstone and Glowstone Blocks, not Dusts.

This mod is fully server-side (provided you use Polymer's generated resource pack on the server) and also works in single player. Recipes can be viewed using Polydex.

## Materials & Gems

Each material has a base modifier and might have a secondary modifier. Gems only have a base modifier and value, with the latter being multiplied by the trinket's material gem modifier. Values with a **+** are added to the base, while values with an **x** are multiplied then added to the base.

As an example, a Quartz Iron trinket will grant the player `[base] + 2` Armor, `[base] + [base] x 0.05` Knockback Resistance, and `[base] + [base] x 0.05 x 1.5` Movement Speed.

| **Materials** | Base Modifier | Value |  Secondary Modifier  | Value | Gem Modifier |
|---------------|:-------------:|:-----:|:--------------------:|:-----:|:------------:|
| Copper        | Armor         | +1    |      --              |  --   | x1 |
| Iron          | Armor         | +2    | Knockback Resistance | x0.05 | x1.5 |
| Gold          | Armor         | +1    | Block Break Speed    | x0.05 | x2 |
| Netherite     | Armor         | +3    | Burning Time         | x-0.1 | x3 |

| **Cut Gems** |            Modifier            |  Value |
|--------------|:------------------------------:|:------:|
| Quartz       | Movement Speed                 | x0.05  |
| Diamond      | Attack Damage                  | x0.05  |
| Emerald      | Luck                           | x0.125 |
| Lapis Lazuli | Oxygen Bonus                   | x0.05  |
| Amethyst     | Armor Toughness                | x0.05  |
| Redstone     | Max Health                     | +2     |
| Resin        | Block Reach                    | +1     |
| Glowstone    | Attack Speed                   | x0.05  |
| Ghast Tear   | Explosion Knockback Resistance | x0.1   |

### Additional Information
PolyDoodads is in beta.
Submit bugs, suggestions and questions to the Issues page on GitHub with the proper label if possible.
