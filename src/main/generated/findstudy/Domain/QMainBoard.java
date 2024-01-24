package findstudy.Domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMainBoard is a Querydsl query type for MainBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMainBoard extends EntityPathBase<MainBoard> {

    private static final long serialVersionUID = -788659583L;

    public static final QMainBoard mainBoard = new QMainBoard("mainBoard");

    public final StringPath content = createString("content");

    public final StringPath dates = createString("dates");

    public final NumberPath<Integer> likes = createNumber("likes", Integer.class);

    public final StringPath link = createString("link");

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final NumberPath<Integer> sort = createNumber("sort", Integer.class);

    public final StringPath tag1 = createString("tag1");

    public final StringPath tag2 = createString("tag2");

    public final StringPath tag3 = createString("tag3");

    public final StringPath title = createString("title");

    public final NumberPath<Integer> views = createNumber("views", Integer.class);

    public final StringPath writer = createString("writer");

    public QMainBoard(String variable) {
        super(MainBoard.class, forVariable(variable));
    }

    public QMainBoard(Path<? extends MainBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMainBoard(PathMetadata metadata) {
        super(MainBoard.class, metadata);
    }

}

