from sqlalchemy.orm import Session

from app.models.phrase import Phrase, ReviewData
from app.schemas.phrases import PhraseCreate

class PhraseService:
    def __init__(self, db_session: Session):
        self.db = db_session

    def get_all_phrases(self) -> list[Phrase]:
        return self.db.query(Phrase).all()
    
    def create_phrase(self, data: PhraseCreate) -> Phrase:
        try:
            phrase = Phrase(
                user_id=data.user_id,
                source_language_id=data.source_language_id,
                target_language_id=data.target_language_id,
                original_text=data.original_text,
                translated_text=data.translated_text,
                pronunciation=data.pronunciation,
            )
            self.db.add(phrase)
            self.db.flush()

            review_data = ReviewData(phrase_id=phrase.id)
            self.db.add(review_data)

            self.db.commit()
            self.db.refresh(phrase)
            return phrase

        except Exception:
            self.db.rollback()
            raise
