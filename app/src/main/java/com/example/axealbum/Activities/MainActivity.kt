package com.example.axealbum.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.example.axealbum.Adapters.YearAdapter
import com.example.axealbum.Dao.DataBase
import com.example.axealbum.Dao.DbWorkerThread
import com.example.axealbum.Data.Landmark
import com.example.axealbum.Data.Memory
import com.example.axealbum.Data.Year
import com.example.axealbum.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mDb : DataBase? = null
    private lateinit var mDbWorkerThread: DbWorkerThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()

        mDb = DataBase.getInstance(this)

        deleteAll()

        val years = ArrayList<Year>()
        val landmarks = ArrayList<Landmark>()
        val memories = ArrayList<Memory>()

        years.add(Year("2018", R.drawable.img_20180913_202537))
        years.add(Year("2019", R.drawable.img_20190329_195156))

        landmarks.add(getLandmark("Abril", R.drawable.img_20180421_200858, "2018"))
        landmarks.add(getLandmark("Mayo", R.drawable.img_20180526_190747, "2018"))
        landmarks.add(getLandmark("Junio", R.drawable.img_20180624_144401, "2018"))
        landmarks.add(getLandmark("Julio", R.drawable.img_20180728_123749, "2018"))
        landmarks.add(getLandmark("Agosto", R.drawable.img_20180807_203106, "2018"))
        landmarks.add(getLandmark("Septiembre", R.drawable.img_20180913_202537, "2018"))
        landmarks.add(getLandmark("Octubre", R.drawable.img_20181002_195438, "2018"))
        landmarks.add(getLandmark("Noviembre", R.drawable.img_20181102_135456_1, "2018"))
        landmarks.add(getLandmark("Diciembre", R.drawable.img_20181201_164605, "2018"))
        landmarks.add(getLandmark("Enero", R.drawable.img_20190101_000340, "2019"))
        landmarks.add(getLandmark("Febrero", R.drawable.img_20190214_074331, "2019"))
        landmarks.add(getLandmark("Marzo", R.drawable.img_20190303_124133_1, "2019"))
        landmarks.add(getLandmark("Abril", R.drawable.img_20190418_151338, "2019"))
        //landmarks.add(getLandmark("Mayo", R.drawable.img_20180913_202537, "2019"))

        memories.add(getMemory("Primera cita :3", R.drawable.img_20180421_200858, "Que decir de nuestra primera cita :33 solo pude encontrar esta fotito :'( pero fue un momento muy lindo conocerteee ❤️. Recuerdo que estaba todo tímido jajajaja \uD83D\uDE48 y no hable muchito xddd, pero igual para mi fue un momento que nunca olvidaré :3", "Abril", "2018"))
        memories.add(getMemory("Primera sangrada :'(", R.drawable.img_20180422_011400, "En la nochecita cuando fui a la casa de mis amigos xddd y me salió sangre :'(. Quizás seguia emocionado de conocerte? :33 jajajaja pensaba que te había contado que sangre xdddd.", "Abril", "2018"))
        memories.add(getMemory("Esperando a mi anechi :3", R.drawable.img_20180428_170904, "Nuestra segunda cita :3. Esperando a mi anechi pensando ya en el rico pinkberry que se avecinaba xddd aunque acabo siendo un banana split igual de rico ❤️", "Abril", "2018"))
        memories.add(getMemory("Banana split :3", R.drawable.img_20180428_175128, "Ya con nuestro rico banana split que me invitaste ❤️. Nuestra segunda cita ya me solté un poco más creo jajajaja :3, recuerdo que estabamos sentaditos al centro y habían varias familias alrededor xddd y que me mencionaste que seguro a mis hermanos les gustaría ese lugar :3", "Abril", "2018"))
        memories.add(getMemory("Paseito por las rotondas xd", R.drawable.img_20180428_200014, "Después de nuestro delicioso banana split fuimos a caminar por las rotondas de por ahi :3, habían varios grupos tocando creo xdd. Esta foto es de un grupo de chica que tocaron recuerdo algo como rock o metal jajajaja, también me acuerdo que habían viejitos bailando en el grupo anterior xdddd y nos reimos un poco de eso :3 jajaja", "Abril", "2018"))
        memories.add(getMemory("Tercera cita ❤️", R.drawable.img_20180505_144504, "Solo pude encontrar esta fotito :'c. Nuestra tercera cita que nos encontramos en brasil con ejercito :3 y ese dia habia una marcha creo, algo relacionado con mi hijo no te metas o algo asi xdddd. Ese día fue cuando fuimos a buscar los crepes/salchipapas creo y estaba cerrado y acabamos comiendo menu :33 jajaja y habia un traficaso en sucre cuando ibamos a mi casa a que te asustes con las peliciulitas :33 no lo pienses, no lo digas xddd", "Mayo", "2018"))
        memories.add(getMemory("Cuarta cita, pinkberry :3", R.drawable.img_20180512_165458, "Por fin comimos nuestro pinkberry :33. Ese día creo fue el que me equivoqueee y me fui al ovalo guiterrez jajajaja, en vez de irme al ovalo de miraflores xdd :'c no conocia muy bien u.u. Me apuré e igual nos vimos prontito :3 y devoramos bien rico ese dia ❤️", "Mayo", "2018"))
        memories.add(getMemory("Caminatas :3", R.drawable.img_20180512_183528, "Después de devorar nos fuimos a caminar me acuerdo :3 caminamos hasta llegar cerca al mar y de ahi nos fuimos de freeeeeeeeeente jajajaja hasta el fondaso :3, me gustó mucho nuestra caminata :3 aunque terminamos muertos los 2 creo xddd", "Mayo", "2018"))
        memories.add(getMemory("Eligiendo nueva camisita xdd", R.drawable.img_20180514_190252, "A los pocos días nomas me pase de mi trabajo a plaza un día y me puse a buscar una nueva camisa para impresionarte :3 jajajaja ❤️, recuerdo que te pregunte cual te parecía mejor y me dijiste que la otra xdddd pero cuando leí tu mensaje ya había comprado :'c jajaja", "Mayo", "2018"))
        memories.add(getMemory("Piano bar ❤️", R.drawable.img_20180526_190747, "No encontré una fotito de nuestra cita anterior :'c, la semana pasada habíamos ido a Doña Julia :3 y nuestra caminata por Magdalena viendo a esa gente que se iba volando como parapenti xddd, y una señorita me agarro de la manito recuerdo :3 jajaja ❤️❤️. Y este día fuimos a comer nuestro salchipapon con vinito a pino bar ❤️. Me encantoooo estuvo bien rico :3 y hablamos de muchas cositass, salimos tarde y nos agarramos de la manito nuevamente ❤️. Todo fue muy lindo ese día, me recontra encanto :33.", "Mayo", "2018"))
        memories.add(getMemory("La integración :p", R.drawable.img_20180528_184000, "El día de la primera integración :3, que le conté a un par de amigos que me gustasssss jajajaja :33 ❤️❤️ Me emborraché regular ese día xddd pero creo que igual aguante mas que la mayoría :33", "Mayo", "2018"))
        memories.add(getMemory("Segundo día de pareja :3 ❤️", R.drawable.img_20180603_191555, "Un día después de que comenzamos a salir :33 después de mi confesión en la brasil xdddd :'c y de nuestro picnic y nuestra caminata larga :33 me alegra muchísimo haberme armado de valor y declararme :3, aunque quizás pude haberlo echo mejor jajajaja, pero igual, fue un momento lindo que comenzo nuestra historia ❤️", "Junio", "2018"))
        memories.add(getMemory("Makis en el APJ :3", R.drawable.img_20180610_143903, "Los makis que devoramos en el APJ que nos recomendaron :3 estaban ricos ❤️ solo ese wasabi rosadito estaba feitoooo jajaja, un día tenemos que volver a ir ehh :3", "Junio", "2018"))
        memories.add(getMemory("Mi chiquita hermosa distraida ❤️", R.drawable.img_20180610_143907, "Mi anechi esperando por los makis :3 ❤️", "Junio", "2018"))
        memories.add(getMemory("Anechi descubriendo mis curiosidades xdddd", R.drawable.img_20180620_220328, "Cuantas curiosidades de mi ya habras visto amor :33 jajajaja solo contigo soy así totalmente libre y sin restricciones :3 ❤️ esa fue una de las primeras seguro xdddd", "Junio", "2018"))
        memories.add(getMemory("Devorando en el mercadito :3", R.drawable.img_20180624_144401, "Cuando fuimos a comer menu al mercadito :3 más curiosidades mias xddd", "Junio", "2018"))
        memories.add(getMemory("Fotito besandonos ❤️", R.drawable.img_20180624_144911, "Fotito dandonos un besote :3 ❤️", "Junio", "2018"))
        memories.add(getMemory("Miradita sexy 😘", R.drawable.img_20180701_162222, "Tengo una miradita curiosaaaaaa jajajaja :3 me encanta como me miras hermosa ❤️", "Julio", "2018"))
        memories.add(getMemory("Primer regalito de aniversario ❤️", R.drawable.img_20180702_223858, "El dunking donuts que me diste en nuestro primer aniversario :33 me encantaron las donutsss ❤️❤️ aunque mis hermanos se comieron algunas :'c pero igual :3", "Julio", "2018"))
        memories.add(getMemory("Primer regalito de aniversario parte 2 xd ❤️", R.drawable.img_20180703_002035, "Mi mewwwww ❤️❤️ mi primer peluchito que me diste amor :3 me encanto un montón ❤️", "Julio", "2018"))
        memories.add(getMemory("Fotito desprevenida :3", R.drawable.img_20180708_144728, "Cómo siempre yo tomándote fotitos desprevenidasss jajajaja, ya deberías estar acostumbrada :3 xdd", "Julio", "2018"))
        memories.add(getMemory("Fridays ❤️", R.drawable.img_20180715_185154, "Nuestra salidita a fridays :3 ❤️ me encanto esas hamburguesitas que comimoss :3 estaban bien ricas y con nuestros tragos :3", "Julio", "2018"))
        memories.add(getMemory("Comidita de fridays :3", R.drawable.img_20180715_185302, "Esas hamburguesitasss esas papitassss esos traguitosss :33 un dia volvemos chi? ❤️", "Julio", "2018"))
        memories.add(getMemory("El concierto de Flow xdd", R.drawable.img_20180718_204732, "El concierto de flow que fui con mis amigos :33 ahora que recuerdo ahi tambien fui con mi pupilo jajajaja, que aun no era mi pupilo en ese entonces xdd. Fue chevere ese dia :3 aunque volvi cansadito xdd", "Julio", "2018"))
        memories.add(getMemory("Fotito sin polito :3", R.drawable.img_20180721_232841, "Una de las primeras fotos que te mande sin polito creo jajaja :3 desde entonces cuantas fotitos comprometedoras mias te habre mandado xddd ❤️❤️ Yo aún no tengo una fotito comprometedora de ti ehh :'cc xd", "Julio", "2018"))
        memories.add(getMemory("Camino a Mala :3", R.drawable.img_20180727_104117, "Nuestro primer viajecitoooooo ❤️❤️❤️ sale el cable con el que estaba cargando mi celu :3 jajaja, que puedo decir, la primera noche que pasamos juntosss, nuestro primer muy muy :3 aunque no terminamos xdddd, un montón de primeras veces pasamos ese viajecito :3", "Julio", "2018"))
        memories.add(getMemory("Menu cerca al hotel :3", R.drawable.img_20180727_152207, "Devorando nuestro menu cerca al hotel :3 aun recuerdo que fuimos caminando por ahí y daba mieditioooo jajajaja, tenían razon que se parece al agustino xddd", "Julio", "2018"))
        memories.add(getMemory("Camino a Azpitia", R.drawable.img_20180728_123749, "En la combi camino a Azpitia :3 Más fotitos desprevenidas para tu consumo :3 xdddd", "Julio", "2018"))
        memories.add(getMemory("Arroz con pato :'(", R.drawable.img_20180728_133754_1, "Ese arroz con pato estaba demasiado durooooo jajajajaja :'ccc no estaba como los otros arroz con patos que había comido antes :'c al final no comí casi nada xdddd :(", "Julio", "2018"))
        memories.add(getMemory("Paisaje con nuestras raspadillas :3", R.drawable.img_20180728_152053, "Una fotito al paisaje de Azpitia con nuestra raspadilla ahí atrás :3,, recuerdo el videito que me tomaste cruzando el río jajajaja :33 te daba miedito curzar amor ? :'cc xdd y que el camino era largo para llegar al río :( jajaja", "Julio", "2018"))
        memories.add(getMemory("Sorpresa :3", R.drawable.img_20180728_152550, "❤️ xddddd", "Julio", "2018"))
        memories.add(getMemory("Segundo aniversario ❤️❤️", R.drawable.img_20180802_082400, "El desayunito que me enviaste a mi casita ❤️ Mis papás se habien ido de viaje creo :33 me encantoooooo todito lo que estaba ahi estaba buenaso ❤️❤️ siempre tan linda tú mi hermosa anett ❤️", "Agosto", "2018"))
        memories.add(getMemory("Infraganti en D'Julia ❤️", R.drawable.img_20180807_203106, "Una fotito más en la colección de fotitos infragantis a mi anechi :3. Esta vez en D'Julia, creo qeu fue la primera vez que fuimos xddd, cuando aún no abrían el de Bolivar :3, y nos enamoramos del milhojas de fresitaaaaa ❤️❤️❤️", "Agosto", "2018"))
        memories.add(getMemory("Vaquita navideña :3", R.drawable.img_20180824_082846, "La vaquita con su vestidito navideñoooooo jajajajaja :3, esa vaquita si se deja poner su ropita y no dice nada ni se lo quita xdddd toda tranquilita ❤️", "Agosto", "2018"))
        memories.add(getMemory("Tequeñitosssssssss y papitasssssss ❤️❤️", R.drawable.img_20180901_175627, "Los tequeñitos y las papitas que me regalo mi hermosa enamorada :33 ❤️❤️ y nuestro vinito también :3, este fue nuestro segundo viajecito que tuvimos en la casita en chalacayo :3. La pase geniaaaaalll ese viajecito, cocinando contigo ❤️ haciendo muymuycito 😘😘 ahí aun no le decíamos así creo jajajaja :3 la piscinitaaaa ❤️ en fin, tantas cositas lindas que pasamos en ese viaje :3, me emborrache mal un día también jajaja \uD83D\uDE48", "Septiembre", "2018"))
        memories.add(getMemory("Mi anechi con Kike :3", R.drawable.img_20180902_142312, "El regalito que te di ese mes :3 tu lobito que busqué para ti mi amor ❤️❤️ Me alegra que te haya gustado y que ahora te acompañe en tus nochecitas :3", "Septiembre", "2018"))
        memories.add(getMemory("Regalito de 3er mes :3 ❤️", R.drawable.img_20180904_225900, "Y aquí esta tu regalitoooooo :3 recuerdo que me lo diste cuando volvimos del viajecito xdd y estabas un poquito triste porque no pudiste llevarlo al viajecito :'c me encantooo ese regalo ❤️ lleno de cositas ricas :3 y hasta ahora conservo tu cajita :3 ❤️. También sale mi moousepad sucio :'cc xddd", "Septiembre", "2018"))
        memories.add(getMemory("D'Julia parte 2 :3", R.drawable.img_20180913_202334, "Nuestro D'Julia :33 pie de limon con milhojas de fresas ❤️3 y nuestros schweppes inflitrados :3 jajaja ❤️ ahora que lo pienso hace tiempo que no tomamos schwepees :'c un día volvemos a tomar hermosa? :3", "Septiembre", "2018"))
        memories.add(getMemory("Fotito de perfil 😘", R.drawable.img_20180913_202537, "Mi fotito de perfil de Whatsapp :33 una de mis fotos favoritas amor ❤️ un beso rico contigo 😘 me gusta tu cabellito, tu collarcito :3 todito ❤️❤️", "Septiembre", "2018"))
        memories.add(getMemory("Fotito con el perrito :3", R.drawable.img_20180915_235512, "La fotito en casa de mi amigo :3 con el perrito que me queria robar jajajaja xddd", "Septiembre", "2018"))
        memories.add(getMemory("Barra libre de la boda :3", R.drawable.img_20180929_223648, "El día que te acompañe a la bodaaaaaa :3 y tomamos nuestros traguitos jajajaja ❤️ entre otras cositas :3 recuerdo que me grabaste devorando infragantimente :'cc xddddd y el día que conocí a tu mami y a tus hermanitos :3 y que recogí lo que tiró tu tío/primooo jajajajajaja, ya sabe tu familia entonces que en un futuro no muy lejano habrá otra boda ❤️❤️ xddddd :3", "Septiembre", "2018"))
        memories.add(getMemory("Selfie en la boda :3", R.drawable.img_20180930_012512, "Ahí ya estaba con mi bouquette de hombres xddd :3", "Septiembre", "2018"))
        memories.add(getMemory("Lonchecito en Starbucks :3", R.drawable.img_20181002_195438, "Mi starbucks que tanto me gustaba ❤️❤️ jajajaja :3 ese pancito y ese muffin tambien estaban potente ehhh ❤️ creo que ese fue un día que teníamos tarjetitas de 2x1 que me dió ruben xdd así que nos salió relativamente a cuenta :3 aunque ahora mi vicio es el shaky shake xddd ❤️", "Octubre", "2018"))
        memories.add(getMemory("Mi moñito :3", R.drawable.img_20181007_205541, "El moñito que me hizo mi anechi :33 recuerdo que me retaste a salir así y  que grabe videito jajajaja y de ahí aprendí a hacerme yo solito :3 me pregunto si aún sabré como hacer bien \uD83D\uDE48 xddd", "Octubre", "2018"))
        memories.add(getMemory("Morsas en cautiverio 😎 xdddd", R.drawable.img_20181013_110324, "Unas morsas acompañándose okno jajajajajajja, mi mami y esa gata gordasa :3 xddd", "Octubre", "2018"))
        memories.add(getMemory("Segunda integración \uD83D\uDE48", R.drawable.img_20181015_175813, "La segunda integración que tuve :3 ahí ya luciéndo la polerita que me habías regalado hace poco nomas :3 ❤️❤️", "Octubre", "2018"))
        memories.add(getMemory("Mi anechi usando mi casaquita ❤️❤️❤️", R.drawable.img_20181018_212252_1, "Te ves tan bella con mi casaquita :3 desde ahí no han sido muchas las veces que te pudé ver con mi casaquita :'cc un día tienes que mandarme fotito tuya usando mi casaca negra ehh :3 ❤️", "Octubre", "2018"))
        memories.add(getMemory("Cumple de la chica más bella del mundo ❤️", R.drawable.img_20181029, "El cumple de la chica más linda, hermosa, sexy e inteligente del mundo ❤️❤️ Espero haberte hecho muy feliz ese día :3 y haberte dado el mejor cumple que hayas tenido hasta ahora ❤️ Recuerdo que me levanté tempranito para ir a buscar tus flores :3, algunas cositas las deje compraditas desde el día anterior y otras el mismo día fui :3 tambien estaba bien rico nuestro trio marinooooo, ese día si que devoramos bien eh :3 ya quiero repetirlo nuevamente ❤️", "Octubre", "2018"))
        memories.add(getMemory("Viajecito a pucusana :3", R.drawable.img_20181102_135456_1, "Mi bella anett pidiendo su comidita en pucusana ❤️", "Noviembre", "2018"))
        memories.add(getMemory("Esa lecheeeee con su gaseosa gorditaaaaa ❤️", R.drawable.img_20181102_141027, "Tú ya sabes como amo la comida marina jajajaja :33 ❤️❤️❤️ esa leche estuvo buenasaaaa, pero creo que me quedo con la de Huarmey xdd :3", "Noviembre", "2018"))
        memories.add(getMemory("Noche pucusaniense xdd", R.drawable.img_20181102_200435, "La vista de noche en pucusana :3, siquiera el velo de la noche tapa todas las casitas de esteras jajajaja xdd", "Noviembre", "2018"))
        memories.add(getMemory("Momento íntimo 😘", R.drawable.img_20181102_202912, "Echaditos listo para una noche de amor, conversaciones y acción 😘😘 xdddd", "Noviembre", "2018"))
        memories.add(getMemory("El michi :3", R.drawable.img_20181103_091119, "El michi :3", "Noviembre", "2018"))
        memories.add(getMemory("Viaje en bote 😎", R.drawable.img_20181103_162226, "Comenzando nuestro viajecito en bote 😎😎😎😎 vimos varios animalitos :33 lo que más me gustó fue ver a los pinguinitos ❤️ y a esos pelícanos también :33 son animales que no tenemos mucha chance de ver 😎. En general me gustó bastante el paseito :3 y no estaba tan caro tampoco xdd 😎", "Noviembre", "2018"))
        memories.add(getMemory("Mi hermanita y la vaquita :3", R.drawable.img_20181109_210126, "En la sesión de fotos que tuvimos en mi cuarto con mi hermanita y la vaquita cuando me dejaron para que la cuide :33", "Noviembre", "2018"))
        memories.add(getMemory("Mi bachiller ❤️", R.drawable.img_20181122_185723, "Mi hermosa bachiller yendo a recoger su bachiller :3 ❤️ LLegamos justo a tiempo ehhh xddd", "Noviembre", "2018"))
        memories.add(getMemory("Diploma :3", R.drawable.img_20181122_185743, "Llegando con tu diploma :3. PD: Qué hace esa chica viéndote con envidia >:(>:( xddd", "Noviembre", "2018"))
        memories.add(getMemory("Miraditas sexys 😘😘", R.drawable.img_20181122_190139, "Esa miradita que me tiras ehh amor :3 ❤️", "Noviembre", "2018"))
        memories.add(getMemory("Chinito Parte 1", R.drawable.img_20181122_194218, "Primera vez que probe el chinitoooooo, me encantó ❤️ Con nuestra gaseosita encima uffff :3 xdddd, una delicia :3. Y qué mejor para compartir esa primera vez que contigo :3", "Noviembre", "2018"))
        memories.add(getMemory("Mi habitat natural xdd", R.drawable.img_20181122_195844, "Siempre yo devorando :33 jajajaja ❤️❤️", "Noviembre", "2018"))
        memories.add(getMemory("Wasabi parte 2 ❤️", R.drawable.img_20181201_164605, "Nuestro retorno triunfante a Wasabi :3 ese día si que arrasamosss jajajajaja no perdamos nadita esa vez xddd :3 yo en especial me comí un montononn xdddd, pero la valió :3 para celebrar nuestro medio añito ❤️ y ahora con este album celebraremos nuestro añito :3", "Diciembre", "2018"))
        memories.add(getMemory("Wasabi parte 2 con makis :3", R.drawable.img_20181201_171015, "Ahí ya con los makis bien serviditos :3 me da penita que vayan a cerrar el local :(:(:(:( sus makis son buenasosssss ❤️ un día tenemos que ir de todas maneras a donde sea que lleven su nuevo local si? :3", "Diciembre", "2018"))
        memories.add(getMemory("MI TOTOROOOOO ❤️❤️", R.drawable.img_20181202_180156, "Mi hermoso totoro ❤️❤️ amo ese peluche :3 es uno de mis preferidos \uD83D\uDE48 ❤️. Me gustó mucho la peliculita :33 y el peluche es tan lindo y adorable ❤️ Muchas gracias por regalarmelo hermosa :33 me recontra encanta ❤️", "Diciembre", "2018"))
        memories.add(getMemory("Starbucks con mi chiquita :33", R.drawable.img_20181207_185913, "Otra nochecita en starbucks con mi amorcito :3 ese saquito te queda muy bien hermosa ❤️❤️❤️ eres muy linda ❤️", "Diciembre", "2018"))
        memories.add(getMemory("Bolivarianooooooo ❤️", R.drawable.img_20181215_132216, "El día que comimos nuestro piqueo marino :33 en el bolivariano ❤️ estaba recontra buenasoooo, disfruté muchísimo de devorar nuestros piqueos juntitos :33 un día tenemos que volver ehh, ahora que ya es un nuevo año y ya tengo mi descuento de bolivariano nuevamente :33", "Diciembre", "2018"))
        memories.add(getMemory("Más bolivariano :3", R.drawable.img_20181215_132337, "Otro ángulo en el bolivariano :33 mi anechi con su polito sexy de peru ❤️❤️", "Diciembre", "2018"))
        memories.add(getMemory("Mi pack \uD83D\uDE48\uD83D\uDE48😘", R.drawable.img_20181229_001551, "Jajajajaja una de tantas fotitos comprometedoras que te he mandado :33 esta incluso creo que es una de las más suaves xdddd ❤️ hace buen tiempo que no te mando fotitos así ehhhh >:3 creo que volver a empezar xddd ❤️", "Diciembre", "2018"))
        memories.add(getMemory("Año nuevo mi amor ❤️", R.drawable.img_20190101_000340, "Owwwww esa fotito juntitos me encanta muchisimo amor ❤️❤️❤️ que decir de ese díaaaa ❤️ mi mejor año nuevo hasta ahora :33, aunque si caminos regular y acabamos cansaditos xddd. Recuerdo que te fui a recoger a tutrabajito :3 pobrecita mi anechi hasta ese día la hacen trabajar :'cc. De ahí fuimos a Miraflores :3 caminamos a larcomar y acabamos cansadasos jajajaja ❤️ Pero igual cada momento a tu lado fue de lo mejor amor ❤️", "Enero", "2019"))
        memories.add(getMemory("Salchipapita en sucre :3", R.drawable.img_20190102_192430, "Una de nuestras priemras salchipapitas en el rinconcito que tenemos en Sucre :33, ahora ya somos caseritos prácticamente jajajaja, aunque ni tanto tampoco xdd pero casi :33", "Enero", "2019"))
        memories.add(getMemory("Piano bar parte 2 :3", R.drawable.img_20190108_205110, "El día que fuimos a piano bar nuevamente ❤️. Es un día medio agridulce para mi :'c. Ese día ya lo vi bastante mal a mi abuelito :(. Estuve casi todo el día en el hospital y enserio me ayudó muchísimo poder quitar todo de mi mente siquiera un rato y estar contigo ❤️. Y de ahí las noticias que me dieron cuando llegue a mi casita :(:( Igual muchas gracias por todo tu apoyo en esos tiempos amor ❤️ no puedo expresar en palabras lo mucho que me ayudaste a superar ese momento triste :( Gracias ❤️", "Enero", "2019"))
        memories.add(getMemory("Paseito por jockey :3", R.drawable.img_20190113_152651, "La primera vez que fuimos al jockey :3 y vimos esas cositas en una tienda curiosa :3. Recuerdo que fuimos para ver una peli pero no me acuerdo cuallll jajajaja :(:(:(:( No estoy seguro si vimos aquaman en ese momento u otra xddd \uD83D\uDE48. Pero si recuerdo que la pasamos bien :33 ❤️", "Enero", "2019"))
        memories.add(getMemory("Naruto 😎❤️", R.drawable.img_20190130_192527, "Nuestro primer naruto también :3. Nos devoramos nuestra carnecita con curry y arroz ❤️❤️ Fue lindo ese momento :33 ver todas las decoracioooones, estar contigo comiendo algo rico ❤️ Siempre me encanta eso :3 y ya tú sabes como me gusta mi comida japones también :3 jajaja ❤️", "Enero", "2019"))
        memories.add(getMemory("Besito :3 ❤️", R.drawable.img_20190130_192842_2, "Nuestro bestio :33 ❤️❤️❤️ y con el totoro de fondo atrás jajaja :3", "Enero", "2019"))
        memories.add(getMemory("Plaza San Martín (creo xddd :'()", R.drawable.img_20190208_193306, "El día que era la misa de mi abuelito que al final nunca sucedió xdd. Te fui a recoger a tu trabajito y estuvimos paseando por ahí un ratito mientras esperabamos :33. De ahí fuimos a la iglesia y al final nunca se llevo a cabo xdd y después a la casa de mis abues :33. Y recuerdo también que ese día con mi tío te llevamos a tu casita :3 y me cobro de más >:( jajajaja. Gracias por acompañarme ese día amor ❤️", "Febrero", "2019"))
        memories.add(getMemory("Tu regalito de San Valentín ❤️❤️", R.drawable.img_20190214_074331, "El regalito que me diste para San Valentín mi amor ❤️❤️ Me encanto muchísisisiiiiiiiiiiiiiiiiiiiimmo ❤️ Fue un detalle muy bello que siempre lo tendré ahi guardadito conmigo ❤️ Muchas gracias por todo lo que me das :3", "Febrero", "2019"))
        memories.add(getMemory("Tu regalito de San Valentín parte 2 ❤️❤️", R.drawable.img_20190214_074802, "Y ahí estan las otras cositas que me diste :3 mis pringlessss ❤️ mi frappu ❤️ el yogurt griego, los bomboncitos :3, el reyenito 😎. Gracias por la comidita que me diste amor ❤️", "Febrero", "2019"))
        memories.add(getMemory("Mi anett con su regalito ❤️", R.drawable.img_20190214_185906, "El regalito que te di yoooo :33 tu tazita roja ❤️ con los bomboncitos y el osito :33 y tu globo metálico 😎😎 espero que te haya gustado muchíiiiiisimo, el primero de tantos regalitos ❤️", "Febrero", "2019"))
        memories.add(getMemory("La mancha reunida 😎😎", R.drawable.img_20190214_221541, "Ahí ya con toda la mancha reunida 😎 mis peluchitos bonitos :3 mi cleffa, mew, totoro y dog ❤️ mis lindos peluches y yo :33", "Febrero", "2019"))
        memories.add(getMemory("Heladito artenasal ❤️", R.drawable.img_20190215_191155, "El día que fuimos por nuestro heladito artesanal :33 ❤️ Estaba bien ricoooo, nunca nos enteramos de donde era no? jajajaja \uD83D\uDE48 pero igual lo disfrutamos en el momento :3", "Febrero", "2019"))
        memories.add(getMemory("Borrachitos en casa de tu amiga :3", R.drawable.img_20190223_192215, "Como olvidar ese díaaaaaa jajajajaja :33 ❤️ que nos emborrachamos en mi casita :33 y estabamos bien rico y apegaditos ❤️ y de ahí salimos a casita de tu amiga por su cumple :3 y tomamos más traguito jajajajaja, por esa zona si corría bala ehhh xddd, menos mál llegamos y salimos bien :3 😎", "Febrero", "2019"))
        memories.add(getMemory("Siete sopas ❤️", R.drawable.img_20190226_190454, "El día que fuimos a siete sopas :33 mi primera vez xddd \uD83D\uDE48 estuvo bien ricoooo, la pase bien lindo contigo ❤️ supero mis expectativas de hecho :33 tenemos que volver prontito 😎", "Febrero", "2019"))
        memories.add(getMemory("Siete sopas parte 2 ❤️", R.drawable.img_20190226_191419, "Ahí nuestros panes al ajo, la sopita y nuestro chaufitaaaa ❤️ Definitivamente ese chaufa estaba recontra ricoooo :33 volvería a ir solo por el chaufa xddd y con la sopa aún mejor :3 ❤️", "Febrero", "2019"))
        memories.add(getMemory("Una sirenita en Huarmey ❤️", R.drawable.img_20190303_123929, "Mi hermosa anett posando en las aguas de Huarmey ❤️ te ves tan linda :33 ❤️ nuestra primera vez en la playita juntitos y fue de lo mejor :3 tantos momentos lindos que pasamos en la playa y en donde nos hospedamos también :3 de ahí salió el gif donde me asomo un poquito del bañooo jajajajajaja y también me mordió el perro liso :'c xdddd pero al final del día fueron memorias irremplazables de otro viajecito juntos lindo ❤️", "Marzo", "2019"))
        memories.add(getMemory("Posando en la playita 😎", R.drawable.img_20190303_124133_1, "Juntitos tomando nuestra fotito en la playa :3 ❤️3", "Marzo", "2019"))
        memories.add(getMemory("Ese cevichito y esa lecheee ❤️", R.drawable.img_20190303_131225, "Devorando nuestro cevichito y nuestra leche de tigre ❤️❤️ estaban bien buenossss :3 y yo como siempre haciendo mis caritas jajaja y sin politooooo \uD83D\uDE48\uD83D\uDE48\uD83D\uDE48 xddd", "Marzo", "2019"))
        memories.add(getMemory("Nuestro último desayunito en Huarmey :'(", R.drawable.img_20190304_093405, "El último desayunito que tuvimos en Huarmey :'c no estaban como los de Pucusana xddd pero igual estaban buenos :33 esos días de mi cumple a tu lado fueron muy hermosos ❤️ gracias por ese viajecito :3 por siempre estar para mi para todito ❤️", "Marzo", "2019"))
        memories.add(getMemory("Rústica :3", R.drawable.img_20190329_195156, "Nuestra salidita con la gente de tu trabajo en Rústica :33, por fin conocí a la gente con la que trabajas xdddd me gustó acompañarte ❤️ y cuando me dijiste para escaparnos cuando se iban al otro lado pense 'Esa mi anechiiiii' jajajajaja ❤️❤️❤️ de ahí fuimos a devorarnos nuestro buen combinadito :33 otra salidita linda contigo ❤️", "Marzo", "2019"))
        memories.add(getMemory("Polladitaaaaa ❤️", R.drawable.img_20190330_144802_1, "Nuestra polladita ❤️❤️ que comimos de tu mami :33 con su gaseositaaaaa ❤️ estaba muy rica :3 bien saladita y crugiente ❤️ me encantó pasar ese rato contigo amor :33 fue la primera vez que entré tu casita también xddd recuerdo que entré con cuidadito por si estaba tu papá jajaja. Vimos nuestra peliculita en tu laptop :33 me dió mucha gracia cuando entró un rato tu Fido xddddd y solo para tomar su aguita y se salio :33 jajajajaja", "Marzo", "2019"))
        memories.add(getMemory("El lomito que descubrimos :3 😎", R.drawable.img_20190405_185314, "Nuestro lomito que descubrimos :33 o bueno que tú descubriste jajaja, estaba bien ricoooooo ❤️ y baratito :3 tenía las 3 Bs de hecho 😎 xdddd, aunque la zonita era un poco maleada jaja :'cc en especial a la hora que fuimos xdd, pero igual volvería a ir contigo cuando quieras linda :3 a seguir devorando 😎❤️", "Abril", "2019"))
        memories.add(getMemory("Charizard ❤️", R.drawable.img_20190416_233420, "El último miembro de la familia de peluchitos :33 ❤️3 mi hermoso charizard ❤️ que ahora se ha vuelto el predilecto :33 jajajaja aunque se pelea el puesto con el totoro xdd pero igual amo a todos mis peluchitos por igual :3 ❤️", "Abril", "2019"))
        memories.add(getMemory("Más curiosidades :3", R.drawable.img_20190418_151338, "Como siempre yo haciendo mis cositas :3 jajajaja ❤️3 dándote mordiditas y tomándonos fotitos :3 ❤️", "Abril", "2019"))
        memories.add(getMemory("Salidita al mall :3", R.drawable.img_20190418_151352, "Nuestra salidita al mall juntitos ❤️ ese día tenias algo que hacer creo y fui a recogerte para pasar un rato por lo menos juntitos :3 siempre me encanta poder pasar aunque sea un ratitín contigo ❤️", "Abril", "2019"))
        memories.add(getMemory("El otaku 😎", R.drawable.img_20190421_132743, "Cuando fuimos al otaku después de nuestro último viajecito  a Chaclacayo :33 y devoramos nuestros makis con más cositas bien rico ❤️3 pobrecita estabas cansadita del larog viajee, encima que tuviste que ir al inglés ese día :'cc pero gracias por acompañarme amor ❤️ siempre me das mis gustitos por más cansadita que estes :3 ❤️", "Abril", "2019"))
        memories.add(getMemory("Crepeeees ❤️", R.drawable.img_20190423_191737, "Nuestros crepeeees que fuimos uno de nuestros martes de antojitos :3 estaban buenos ehh 😎 y el precio no estaba mal tampoco :3, creo que le llevaste uno a tus hermanitos y no les gustó mucho no? jajaja, pero a mi si :33 un día podríamos volver también ❤️", "Abril", "2019"))

        insertLandmarksInDb(landmarks)
        insertMemoriesInDb(memories)

        val adapter = YearAdapter(years)
        yearsRecyclerView.adapter = adapter

        val layoutManager = GridLayoutManager(this, 3)
        yearsRecyclerView.layoutManager = layoutManager
    }

    fun getEmoji(unicode: Int): String { return String(Character.toChars(unicode)) }

    private fun getLandmark(title: String, image: Int, year: String): Landmark {
        val landmark = Landmark()
        landmark.title = title
        landmark.image = image
        landmark.year = year
        return landmark
    }

    private fun getMemory(title: String, image: Int, comment: String, landmark: String, year: String): Memory {
        val memory = Memory()
        memory.title = title
        memory.image = image
        memory.comment = comment
        memory.landmark = landmark
        memory.year = year
        return memory
    }

    private fun deleteAll() {
        val task = Runnable {
            mDb?.landmarkDao()?.deleteAll()
            mDb?.memoryDao()?.deleteAll()
        }
        mDbWorkerThread.postTask(task)
    }

    private fun insertLandmarksInDb(landmarks: List<Landmark>) {
        val task = Runnable {
            mDb?.landmarkDao()?.deleteAll()

            for (i in 0 until landmarks.size) {
                mDb?.landmarkDao()?.insert(landmarks[i])
            }
        }
        mDbWorkerThread.postTask(task)
    }

    private fun insertMemoriesInDb(memories: List<Memory>) {

        val task = Runnable {
            mDb?.memoryDao()?.deleteAll()

            for (i in 0 until memories.size) {
                mDb?.memoryDao()?.insert(memories[i])
            }
        }
        mDbWorkerThread.postTask(task)
    }
}
