# Computational Mathematics Labs
Subject in the 2nd year, 4th semester

<br>
[Лабораторная 1](#https://github.com/efobo/ITMO_Computational_mathematics_Labs#лабораторная-работа-1-системы-линейных-алгебраических-уравнений)


***

# Лабораторная работа 1. Системы линейных алгебраических уравнений<a name="lab1"></a>

<p>Вам будет выдан один из следующих вариантов:</p>

<p><i>Вариант: Метод простых итераций</i></p>


<ul>
  <li>Прямые методы:
    <ul>
      <li>Метод Гаусса</li>
      <li>Метод Гаусса с выбором главного элемента</li>
    </ul>
  </li>
  <li>Итерационные методы:
    <ul>
      <li><b>Метод простых итераций</b></li>
      <li>Метод Гаусса-Зейделя</li>
    </ul>
  </li>
</ul>

<p>В лабораторной работе Вам предлагается реализовать один из методов, который бы 
позволял находить столбец неизвестных для системы линейных алгебраических уравнений. Размер 
системы предполагается ограниченным 20, это означает, что Вашу программу после реализации 
необходимо будет проверить на матрице размером 20 x 20 + 20 элементов (количество неизвестных 
в матрице A и столбце B). Исходя из этого условия матрицы такого размера должны иметь 
возможность каким-то образом попадать в Ваш численный метод. Например, ввод данных в 
ручном формате может быть затруднительным для 420 элементов системы с 20 неизвестными.</p>

<p>Традиционно предлагается сделать следующие методы ввода данных в программу:</p>

<ul>
  <li>Пользовательский ввод;</li>
  <li>Ввод данных из файла;</li>
  <li>Генерация случайных матриц.</li>
</ul>

<p><b>Пользовательский ввод данных</b> позволяет протестировать программу относительно просто и 
не требует дополнительных усилий со стороны разработчика, однако, по этой же причине вполне 
может быть заменён исключительно вводом из файла. С другой стороны, если такой способ ввода 
уже существует, то почему бы не сделать его приятным для пользователя?</p>

<p>Например, в случае, если пользователь вводит некорректные данные (буквы вместо чисел, две 
запятые или точку вместо запятой в вещественных чисел, пробел между числом и знаком минуса –
всё, что не может быть распознано как число; не правильное количество данных; матрицу, 
определитель которой будет свидетельствовать об отсутствии решений; отрицательное значение 
размера матрицы и пр.) пользовательский ввод необходимо прервать с просьбой повторного ввода 
как можно раньше, а не продолжать запрашивать данные, чтобы сообщить об ошибке в самом 
конце. Особо необходимо отметить, что все подобные сценарии ошибок пользовательского ввода 
должны быть обработаны, при чём не только для ввода данных через терминал, но и для, 
например, ввода данных из файла.</p>

<p><b>Ввод данных из файла</b> представляется хорошим способом проверки корректности работы 
численного метода, особенно когда речь идёт о матрицах большого размера. При вводе данных из 
терминала вполне возможно ошибиться, а при генерации случайной матрицы результат трудно 
проверяем. При работе с файлами данных необходимо продумать формат данных, с которыми 
будет проводиться работа: какие данные содержатся в самом файле, а какие вводятся отдельно 
пользователем. Например, для итерационных методов файл с данными будет содержать размер 
матрицы и саму матрицу, а точность приближения будет задавать пользователь. Так же 
необходимо продумать вопрос локализации: какой знак для вещественных чисел Вы будете 
использовать: точку или запятую. Для самопроверки численного метода в системе Moodle и в 
группе VK загружены несколько файлов с решениями. При защите лабораторной работы 
преподаватель может попросить Вас продемонстрировать работу Вашей программы на каком-то 
ином файле для того, чтобы проверить, действительно-ли Ваш реализованный численный метод 
считает результат достаточно точно.
</p>

<p><b>Генерация случайных матриц</b> может быть реализована двумя основными способами:</p>

<ul>
  <li>Простая генерация матриц А, удовлетворяющей условиям применимости метода, и столбца 
B;</li>
  <li>Генерация столбца неизвестных и генерация матрицы A, удовлетворяющей условиям 
применимости метода, затем расчёт правой части уравнений и «забывание» столбца 
неизвестных. В таком случае можно сравнить рассчитанные и «загаданные» значения 
неизвестных.</li>
</ul>

<p>Обратите внимание, что в обоих случаях имеет смысл генерация только матриц, имеющих 
решение реализуемым Вами численном методе. Чем больше будет размер матрицы, тем меньше 
будет вероятность сгенерировать матрицу, которая будет подходить под условия применимости 
метода! Особенно это актуально для итерационных методов. Обратите внимание, что условия 
сходимости итерационных процессов для методов простой итерации и для метода Гаусса-Зейделя 
– отличаются.</p>


<p>В отчёт по лабораторной работе в раздел с примерами нужно будет вставить в том числе 
пример с матрицей некоторого размера большего, чем обычно считают, например системы с 5-6 неизвестными. 
  Вывод матриц систем с 20 неизвестными можно продемонстрировать на защите 
работы, однако в отчёте они как правило помещаются плохо.</p>


<p>При анализе реализованного численного метода Вам необходимо запускать его на различных 
наборах данных, а также попытаться понять работает ли программа на этом наборе данных правильно, 
  если не работает, то должна ли работать и если нет, то почему. Так же предлагается проанализировать 
  скорость работы численного метода. Сделать это можно путём сравнения аналитического значения алгоритмической 
  сложности метода (по составленной ранее блок-схеме) и путём реальных замеров скорости работы численного метода различными инструментами 
выбранного вами языка программирования. Например, в языке Python это можно сделать до и 
  после вызова численного метода использовать команду <code>datetime.datetime.now()</code>, а затем вычесть 
  полученные значения. Аналогично в Java можно использовать <code>System.currentTimeMillis()</code>. </p>
  
  
  <p>Можно также использовать различные средства для анализа потребляемой памяти. Например, 
для Java можно использовать VisualVM для просмотра динамики работы JVM, либо запрашивать 
  текущее состояние памяти через запуск в JVM команд: <code>Runtime.getRuntime().freeMemory()</code>. 
Аналогично можно поступать и для других языков программирования по Вашему желанию.
</p>


<p>Для начала тестирования Вашей программы предлагаю использовать два простейших набора 
данных:</p>


<ul>
  <li>Все элементы матрицы A равны 0, а значения столбца B – любые не одинаковые числа;</li>
  <li>Все элементы матрицы A равны 1, а значения столбца B – любые не одинаковые числа.</li>
  <li>Любая матрица, определитель которой для самопроверки Вы можете рассчитать при 
помощи метода Крамера.</li>
</ul>

<p>Обратите также внимание, что для прямых и итерационных методов вывод в консоль должен 
отличаться.</p>

<p>Для прямых методов (метод Гаусса и метод Гаусса с выбором главных элементы) должно быть 
реализовано:</p>

<ul>
  <li>Вычисление определителя;</li>
  <li>Вывод треугольной матрицы (включая преобразованный столбец В);</li>
  <li>Столбец неизвестных;</li>
  <li>Столбец невязок.</li>
</ul>

<p>Для итерационных методов (метод простой итерации и метод Гаусса-Зейделя) должно быть 
реализовано:</p>

<ul>
  <li>Точность задается пользователем;</li>
  <li>Проверка диагонального преобладания для соответствующего метода
(В случае, если диагональное преобладание в изначальной матрице отсутствует -
предлагается сделать перестановку строк/столбцов до тех пор, пока преобладание не 
будет достигнуто. В случае невозможности достижения диагонального преобладания -
выводить сообщение.);</li>
  <li>Столбец неизвестных;</li>
  <li>Количество итераций, за которое было найдено решение;</li>
  <li>Столбец погрешностей.</li>
</ul>

<p>Обращаю ваше внимание, что для итерационных методов в пользовательском выводе вашей 
программы должны быть только значения неизвестных, полученные на последней выполненной 
итерации, а не на всех выполненных итерациях. Аналогично со столбцом погрешностей – они 
должны соответствовать лишь последней итерации.</p>

<p>Для прямых методов часто представляет собой трудность расчёт невязки. Невязка представляет 
собой разницу между левой и правой частью уравнения. Таким образом для каждого уравнения в 
системе будет рассчитываться собственное значение невязки. Если правая часть уравнения у нас 
уже есть – это соответствующий элемент матрицы B, то левую часть нужно рассчитать, подставив на места неизвестных 𝑥 рассчитанные при помощи численного метода значения, а затем произведя 
операции умножения и сложения. Невязка – это то, на сколько правая часть уравнения не равна 
левой, в то время как в уравнении по определению обе части должны быть равны.</p>

<p>При расчёте определителя для прямых методов и в особенности для метода Гаусса с выбором 
главного элемента, необходимо помнить и учитывать свойства определителя.</p>

<p>В выводе помимо анализа самого метода необходимо сравнить его со вторым методом той же 
категории (например, для метод простой итерации сравнить с методом Гаусса-Зейделя), а также 
прямые методы в целом сравнить с итерационными.</p>

***

# Лабораторная работа 2. Системы нелинейных уравнений

<p><i>Вариант: 2бв</i></p>

<p>В лабораторной работе 2 Вам необходимо будет реализовать три численных метода. Два из 
них будут решать нелинейные уравнения и ещё один будет решать систему нелинейных уравнений. 
Вариант будет выдан в виде 1аб, где цифра относится к методу решения систем нелинейных 
уравнений, а буквы – к решению нелинейных уравнений:</p>
<p>Решение нелинейных уравнений:</p>
<ul>
    <li>метод деления пополам</li>
    <li><b>метод хорд</b></li>
    <li><b>метод касательных</b></li>
    <li>метод простой итерации</li>
</ul>

<p>Решение систем нелинейных уравнений:</p>
<ul>
    <li>метод Ньютона</li>
    <li><b>метод простой итерации</b></li>
</ul>

<p>В связи с тем, что для решения нелинейных уравнений вам предлагается реализовать 2 
метода, имеет смысл сравнить полученный результат между друг другом. Например, решением 
уравнения по методу деления пополам мы получили 5,1, а методом хорд получили 4,9, тогда 
разница между двумя методами будет равна 0,2. Соответственно, вычисления двух методов для 
одного уравнения должно производиться одновременно, без уточнения у пользователя каким 
именно численным методом он хотел бы рассчитать уравнение.</p>

<p>Нелинейные уравнения и системы нелинейных уравнений студенту предлагается выбрать 
самостоятельно. Тем не менее, необходимо учитывать области допустимых значений. Для 
примеров можно выбрать как нелинейные алгебраические, так и не алгебраические нелинейные 
уравнения.
</p>

***

# Лабораторная работа 3. Численное интегрирование
<p><i>Вариант: Метод прямоугольников</i></p>

<p>Среди методов численного интегрирования вам будет предложено реализовать один из 
следующих методов:</p>

<ul>
    <li><b>Метод прямоугольников;</b></li>
    <li>Метод трапеций;</li>
    <li>Метод Симпсона (метод парабол).</li>
</ul>

<p>При этом, если Вам было задано реализовать метод прямоугольников, то должно быть 
реализовано все три его варианта:</p>

<ul>
    <li>Метод левых прямоугольников;</li>
    <li>Метод правых прямоугольников;</li>
    <li>Метод средних прямоугольников;</li>
</ul>

<p>В этом случае пользователю нужно лишь единожды задать параметры, для которых он хочет 
получить результат, а применено должно быть все три варианта, без уточнения каким именно 
вариантом метода прямоугольника ему следует рассчитать интеграл.</p>

<p>При расчёте интеграла необходимо учитывать ОДЗ функции и необходимые и достаточные 
условия существования определённого интеграла. Если на интервале интегрирования существует 
устранимый разрыв первого рода, то его следует устранить одним из способов (с уточнением 
пользователю, каким именно способом Вы собираетесь его устранять) и выполнить расчёт 
интеграла. Например, может быть выполнен расчёт левой части интеграла от разрыва и правой в 
отдельности. Альтернативным методом устранения разрыва будет принять алгоритмическое 
среднее от значения от двух соседних точках функции 𝑓(𝑥 − 𝜀), 𝑓(𝑥 + 𝜀), где 𝜀 – наперёд заданная
малая постоянная, которая может быть зависима от текущего шага разбиения интеграла. В обоих 
случаях Вы должны продумать стратегию алгоритма устранения разрыва при попадании его на 
самую границу интервала.</p>

<p>Важным вопросом, который надо рассмотреть в данной лабораторной работе – погрешность 
квадратурных формул интегрирования, соответственно обозначаемых как 𝑟𝑖 на каждом разбиении 
интеграла и 𝑅 на глобальном интервале [𝑎; 𝑏], на котором производится интегрирование функции. 
На основании того факта, что интеграл по определению является пределом суммы бесконечно 
малых разбиений, без уточнений как именно это разбиение будет производиться, то в пределе 
абсолютно все методы численного интегрирования будут давать верный результат при правильном 
их применении. Поэтому для сравнения методов между собой следует анализировать 
максимальную величину шага разбиения, либо количество разбиений (особенно для методов с 
динамическим, а не постоянным шагом). В прямую это можно сделать при помощи сравнения 
полученного значения интеграла с его аналитическим значением, рассчитанным, например, по 
формуле Ньютона-Лейбница или табличных значений интеграла. Однако, для глобальных выводов 
необходимо также анализировать погрешности самих квадратурных формул 𝑅, которые также 
дают ответ на то, какие функции следует интегрировать при помощи тех или иных численных 
методов интегрирования. Ваши рассуждения, наблюдения и результаты анализа и должны 
составлять основу вывода в данной лабораторной работе.</p>

***

# Лабораторная работа 4. Интерполяция и аппроксимация
<p><i>Вариант: Метод сплайнов</i></p>

<p>В лабораторной работе 4 будет предложено реализовать наиболее известный метод 
аппроксимации: аппроксимация методом наименьших квадратов и методы интерполяции:</p>

<ul>
    <li>Метод интерполяции полиномом Ньютона;</li>
    <li>Метод интерполяции полиномом Лагранжа;</li>
    <li><b>Метод интерполяции кубическими сплайнами</b></li>
</ul>

<p>Обратите внимание, что начиная с данной лабораторной работы Вам необходимо строить 
графики функций. На графике необходимо отобразить исходные точки и точки, полученные при 
помощи разработанного численного метода, согласно варианту.</p>

<p>Во всех случаях вам необходимо сгенерировать набор входных точек, на которых вы будете 
тестировать свою программу. Набор точек должен представлять собой некоторое количество пар 
(x; y) полученных из какой-то существующей функции. Чтобы сгенерировать такой набор данных 
рекомендуется использовать подготовленную функцию, поведение которой вам уже известно.
Предпочтительно иметь несколько наборов входных данных из нескольких функций. Также желательно добавить шум (отклонения значений y от математического). Имеет смысл также
отобразить на графике функцию, на основе которой был получен набор данных.</p>

<p>Для варианта аппроксимации следует применить метод аппроксимации дважды: второй раз 
метод должен быть запущен на укороченном списке входных точек, при чём исключена должна 
быть точка, имеющая наибольший квадрат отклонения после первого запуска. Иначе говоря,
алгоритм, следующий:</p>

<ol>
    <li> Задается произвольный (полученный ранее) набор значений пар (𝑥; 𝑦).</li>
    <li>Задается аппроксимирующая функция.</li>
    <li>Рассчитываются программой коэффициенты аппроксимирующей функции.</li>
    <li>Производится поиск точки с наибольшим отклонением от значения, полученного при помощи 
аппроксимирующего многочлена.</li>
    <li> Найденная точка исключается и производится пересчёт коэффициентов аппроксимирующего 
многочлена (см. п.3).</li>
    <li>Строится график, содержащий в себе две функции (1 - до исключения, 2 - после исключения 
и пересчёта) и набор заданных изначально точек (пар значений (𝑥, 𝑦)).</li>
    <li>Помимо этого, отдельно на экран выводятся полученные значения аппроксимирующих 
коэффициентов.</li>
</ol>

<p>В качестве аппроксимирующей функции следует выбрать несколько наиболее популярных 
аппроксимирующих функций: линейная, квадратичная, логарифмическая, тригонометрическая и 
т. п.</p>

<p>Для расчёта СЛАУ рекомендуется использовать методы решения СЛАУ из лабораторной 
работа 1.</p>

<p>При анализе методов в лабораторной работе следует обратить внимание на сценарии их 
применения, основываясь на особенностях методов.</p>

***

# Лабораторная работа 5. Численное дифференцирование и задача Коши
<p><i>Вариант: Метод Милна</i></p>

<p>В лабораторной работе 5 вам будет предложено реализовать один из методов решения 
задачи Коши – решения дифференциального уравнения по заданным начальным условиям.</p>

<p>Одношаговые методы:</p>
<ul>
    <li>Метод Эйлера</li>
    <li>Усовершенствованный метод Эйлера (не путать с улучшенным методом 
Эйлера)</li>
    <li>Метод Рунге-Кутты 4-го порядка</li>
</ul>

<p>Многошаговые методы:</p>

<ul>
    <li><b>Метод Милна</b></li>
    <li>Метод Адамса (не путать с методом Адамса-Башфорта)</li>
</ul>

<p>В лабораторной работе Вам предлагается подготовить некоторое количество 
дифференциальных уравнений, которые сможет решать пользователь, вводя собственные 
начальные условия. Помимо этого, предполагается, что пользователь сможет выбирать как далеко
нужно решить дифференциальное уравнение от начального условия. При анализе и демонстрации 
лабораторной работы рекомендуется исследовать не только различные дифференциальные уравнения, но и различные начальные условия для одного дифференциального уравнения – график 
решения при этом вероятно будет меняться.</p>

<p>На графике необходимо показать начальное условие и полученное решение 
дифференциального уравнения. Полезно также отобразить аналитическое решение 
дифференциального уравнения, чтобы иметь возможность сравнить и проанализировать
накопление или не накопление ошибки. Аппроксимацию в обоих случаях (для аналитического и для 
полученного решения) необходимо выполнять по методу из лабораторной работы 4 (если в 
лабораторной работе 4 вашим вариантом была аппроксимация, то удваивать количество графиков
– до и после удаления точек, как в лабораторной работе 4 - рисовать не нужно, метод должен быть
применён только один раз).</p>

<p>В анализе лабораторной работы следует обратить внимание на понятие устойчивости 
решения в терминах решения дифференцирования – будет ли ошибка накапливаться и возрастать 
по мере удаления от начального условия, а также на сценариях применения, основанных на 
особенностях методов.</p>

