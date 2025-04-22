package univer;

import java.util.Arrays;
import lombok.*;

/*
Написать класс Автомобиль. Он должен содержать:
    поле типа String, хранящее марку автомобиля,
    метод для получения марки автомобиля,
    метод для модификации марки автомобиля,
    
    внутренний класс Модель, имеющий поля название модели и её цену, а также конструктор (класс Автомобиль хранит массив Моделей),
    метод для модификации значения названия модели,
    
    метод, возвращающий массив названий всех моделей,
    метод, возвращающий массив значений цен моделей,

    метод для получения значения цены модели по её названию,
    метод для модификации значения цены модели по её названию,

    метод добавления названия модели и её цены (путем создания нового массива Моделей), использовать метод Arrays.copyOf(),
    метод удаления модели с заданным именем и её цены, использовать методы System.arraycopy, Arrays.copyOf(),
    метод для получения размера массива Моделей.
    Конструктор класса должен принимать в качестве параметров значение Марки автомобиля и размер массива Моделей
 */
public class Automobile {
    @Getter
    @Setter
    private String mark;

    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public class Model{
        
        private String name;
        private Double price;
    }

    private Model[] models;

    public Automobile(String mark, int len) 
    {
        this.mark = mark;
        if (len < 0)
            throw new IllegalArgumentException("len < 0");
        models = new Model[len];
    }

    public String[] getModelNames()
    {
        return Arrays.stream(models).map( m -> m.getName()).toArray(String[]::new);
    }

    public Double[] getModelPrices()
    {
        return Arrays.stream(models).map( m -> m.getPrice()).toArray(Double[]::new);
    }

    public Double getModelPriceByName(String name)
    {
        return Arrays.stream(models)
            .filter( m -> m.getName() == name)
            .findFirst()
            .map(m -> m.getPrice())
            .get();
    }

    public void setModelPriceByName(String name, Double price)
    {
        Arrays.stream(models)
            .filter( m -> m.getName() != null && m.getName().equals(name))
            .findFirst()
            .ifPresent(m -> m.setPrice(price));
    }

    public void addModel(String name, Double price)
    {
        var mod = new Model(name, price);
        models = Arrays.copyOf(models, models.length + 1);
        models[models.length - 1]= mod;
    }

    

    public void delModel(String name)
    {
        // models = Arrays.stream(models)
        //     .filter(m -> 
        //          m.getName() != null &&
        //         !m.getName().equals(name))
        //     .toArray(Model[]::new);

        int index = 0;
        for (;
            index < models.length &&
            (models[index].getName() != null &&
            !models[index].getName().equals(name));
            index++
        ) {}

        // System.arraycopy(models, 0, models, 0, index);
        if (index < models.length - 1)
            System.arraycopy(models, index + 1, models, index, models.length - index);
        models = Arrays.copyOf(models, models.length - 1);
    }

    public int getModelsCount()
    {
        return models.length;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName() + "\n");
        sb.append("mark: " + mark + "\n");
        sb.append("models ("+models.length+")"+":" + "\n");
        for (Model model : models) {
            sb.append(model);
            sb.append("\n");
        }
        return sb.toString();
    }
}
