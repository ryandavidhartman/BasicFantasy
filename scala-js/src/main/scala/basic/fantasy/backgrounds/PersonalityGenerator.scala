package basic.fantasy.backgrounds

import basic.fantasy.Roller

object PersonalityGenerator {

  val Personalities = Vector("Brave", "Careless", "Cautious", "Cheerful", "Courteous", "Dishonest",
    "Forgiving", "Friendly", "Helpful", "Honest", "Kind", "Lazy", "Modest", "Nosy", "Prankish", "Rude",
    "Suspicious", "Talkative", "Trusting", "Wasteful", "Humble", "Arrogant", "Pious", "Accessible", "Absentminded",
    "Abrasive", "Invulnerable", "Hostile", "Vivacious", "Active", "Aggressive", "Abrupt", "Kind", "Ignorant", "Warm",
    "Adaptable", "Ambitious", "Agonizing", "Knowledge", "Imitative", "Well-bred", "Admirable", "Amusing", "Aggressive",
    "Leader", "Impatient", "Well-read", "Adventurous", "Artful", "Aimless", "Leisurely", "Impractical", "Well-rounded",
    "Agreeable", "Ascetic", "Aloof", "Logical", "Imprudent", "Winning", "Alert", "Authoritarian", "Amoral", "Lovable",
    "Impulsive", "Wise", "Amiable", "Big-thinking", "Angry", "Loyal", "Inconsiderate", "Witty", "Anticipative",
    "Boyish", "Anxious", "Lyrical", "Incurious", "Youthful", "Appreciative", "Breezy", "Apathetic", "Magnanimous",
    "Indecisive", "Thievish", "Articulate", "Businesslike", "Arbitrary", "Many-sided", "Indulgent", "Thoughtless",
    "Aspiring", "Busy", "Argumentative", "Masculine", "Inert", "Timid", "Athletic", "Casual", "Arrogant", "Mature",
    "Inhibited", "Transparent", "Attractive", "Cerebral", "Artificial", "Methodical", "Insecure", "Treacherous",
    "Balanced", "Chummy", "Asocial", "Meticulous", "Insensitive", "Trendy", "Benevolent", "Circumspect", "Bewildered",
    "Moderate", "Insincere", "Troublesome", "Brilliant", "Competitive", "Bizarre", "Modest", "Insulting",
    "Unappreciative", "Calm", "Complex", "Bland", "Multi-leveled", "Intolerant", "Uncaring", "Capable", "Confidential",
    "Blunt", "Neat", "Irascible", "Uncharitable", "Captivating", "Conservative", "Boisterous", "Objective",
    "Irresponsible", "Unconvincing", "Caring", "Contradictory", "Brittle", "Observant", "Irritable", "Uncooperative",
    "Challenging", "Crisp", "Brutal", "Open", "Lazy", "Uncreative", "Charismatic", "Cute", "Calculating", "Optimistic",
    "Malicious", "Uncritical", "Charming", "Deceptive", "Callous", "Orderly", "Mannerless", "Unctuous", "Cheerful",
    "Determined", "Cantankerous", "Organized", "Mechanical", "Undisciplined", "Clean", "Dominating", "Careless",
    "Original", "Meddlesome", "Unfriendly", "Clear-headed", "Dreamy", "Charmless", "Painstaking", "Melancholic",
    "Ungrateful", "Clever", "Driving", "Childish", "Passionate", "Messy", "Unhealthy", "Colorful", "Droll", "Clumsy",
    "Patient", "Miserable", "Unimaginative", "Companionly", "Dry", "Coarse", "Patriotic", "Miserly", "Unimpressive",
    "Compassionate", "Earthy", "Cold", "Peaceful", "Misguided", "Unlovable", "Conciliatory", "Effeminate", "Colorless",
    "Perceptive", "Mistaken", "Unpolished", "Confident", "Emotional", "Complacent", "Perfectionist", "Money-minded",
    "Unprincipled", "Conscientious", "Enigmatic", "Complaining", "Personable", "Moody", "Unrealistic", "Considerate",
    "Experimental", "Compulsive", "Persuasive", "Morbid", "Unreflective", "Constant", "Familial", "Conceited",
    "Playful", "Muddle-headed", "Unreliable", "Contemplative", "Folksy", "Condemnatory", "Polished", "Naive",
    "Unrestrained", "Cooperative", "Formal", "Conformist", "Popular", "Narcissistic", "Unstable", "Courageous",
    "Freewheeling", "Confused", "Practical", "Narrow", "Vacuous", "Courteous", "Frugal", "Contemptible", "Precise",
    "Narrow-minded", "Vague", "Creative", "Glamorous", "Conventional", "Principled", "Negative", "Venomous",
    "Cultured", "Guileless", "Cowardly", "Profound", "Neglectful", "Vindictive", "Curious", "High-spirited", "Crass",
    "Protean", "Neurotic", "Vulnerable", "Daring", "Hurried", "Crazy", "Protective", "Nihilistic", "Weak", "Debonair",
    "Hypnotic", "Criminal", "Providential", "Obnoxious", "Willful", "Decent", "Iconoclastic", "Critical", "Prudent",
    "Obsessive", "Individualistic", "Decisive", "Idiosyncratic", "Crude", "Punctual", "Obvious", "Innovative",
    "Dedicated", "Impassive", "Cruel", "Purposeful", "Odd", "Inoffensive", "Deep", "Impersonal", "Cynical", "Rational",
    "Offhand", "Insightful", "Dignified", "Impressionable", "Decadent", "Realistic", "One-dimensional", "Insouciant",
    "Directed", "Intense", "Deceitful", "Reflective", "One-sided", "Intelligent", "Disciplined", "Invisible", "Delicate",
    "Relaxed", "Opinionated", "Intuitive", "Discreet", "Irreligious", "Demanding", "Reliable", "Opportunistic",
    "Unhurried", "Dramatic", "Irreverent", "Dependent", "Resourceful", "Oppressed", "Uninhibited", "Dutiful",
    "Maternal", "Desperate", "Respectful", "Outrageous", "Unpatriotic", "Dynamic", "Modern", "Destructive",
    "Responsible", "Paranoid", "Unpredictable", "Earnest", "Moralistic", "Devious", "Responsive", "Passive",
    "Unsentimental", "Ebullient", "Mystical", "Difficult", "Reverential", "Pedantic", "Whimsical", "Educated",
    "Neutral", "Disconcerting", "Romantic", "Perverse", "Irrational", "Efficient", "Noncommittal", "Discontented",
    "Rustic", "Petty", "Gullible", "Elegant", "Noncompetitive", "Discouraging", "Sage", "Plodding", "Hateful",
    "Eloquent", "Obedient", "Discourteous", "Sane", "Pompous", "Haughty", "Empathetic", "Old-fashioned", "Dishonest",
    "Scholarly", "Possessive", "Hedonistic", "Energetic", "Ordinary", "Disloyal", "Scrupulous", "Power-hungry",
    "Hesitant", "Enthusiastic", "Outspoken", "Disobedient", "Secure", "Predatory", "Hidebound", "Esthetic",
    "Paternalistic", "Disorderly", "Selfless", "Prejudiced", "High-handed", "Exciting", "Physical", "Disorganized",
    "Self-critical", "Presumptuous", "Idealistic", "Extraordinary", "Placid", "Disputatious", "Self-defacing",
    "Pretentious", "Imaginative", "Fair", "Political", "Disrespectful", "Self-denying", "Prim", "Impressive",
    "Faithful", "Predictable", "Disruptive", "Self-reliant", "Procrastinating", "Incisive", "Farsighted", "Preoccupied",
    "Dissonant", "Self-sufficent", "Provocative", "Incorruptible", "Felicific", "Private", "Distractible", "Sensitive",
    "Puritanical", "Independent", "Firm", "Progressive", "Disturbing", "Sentimental", "Quirky", "Nobel", "Flexible",
    "Proud", "Dogmatic", "Seraphic", "Reactionary", "Unambitious", "Focused", "Pure", "Domineering", "Serious",
    "Reactive", "Unceremonious", "Forceful", "Questioning", "Dull", "Sexy", "Regimental", "Unchanging", "Forgiving",
    "Quiet", "Easily", "Sharing", "Regretful", "Undemanding", "Forthright", "Religious", "Egocentric", "Shrewd",
    "Repentant", "Unfathomable", "Freethinking", "Reserved", "Envious", "Simple", "Repressed", "Frightening",
    "Friendly", "Restrained", "Erratic", "Skillful", "Resentful", "Frivolous", "Fun-loving", "Retiring", "Escapist",
    "Sober", "Ridiculous", "Gloomy", "Gallant", "Sarcastic", "Extravagant", "Sociable", "Rigid", "Graceless",
    "Generous", "Self-conscious", "Extreme", "Solid", "Ritualistic", "Greedy", "Gentle", "Sensual", "Faithless",
    "Sophisticated", "Ruined", "Grim", "Genuine", "Skeptical", "Discouraged", "Spontaneous", "Sadistic",
    "Individualistic", "Good-natured", "Smooth", "Fanatical", "Sporting", "Sanctimonious", "Incisive", "Gracious",
    "Soft", "Fanciful", "Stable", "Scheming", "Incorruptible", "Hardworking", "Solemn", "Fatalistic", "Steadfast",
    "Scornful", "Independent", "Healthy", "Solitary", "Fawning", "Steady", "Secretive", "Unchanging", "Hearty", "Stern",
    "Fearful", "Stoic", "Sedentary", "Undemanding", "Helpful", "Strict", "Fickle", "Strong", "Selfish", "Unfathomable",
    "Heroic", "Stubborn", "Fiery", "Studious", "Self-indulgent", "Graceless", "High-minded", "Stylish", "Fixed",
    "Suave", "Shallow", "Greedy", "Honest", "Subjective", "Flamboyant", "Subtle", "Shortsighted", "Grim", "Honorable",
    "Surprising", "Foolish", "Sweet", "Sloppy", "Impressive", "Humble", "Soft", "Forgetful", "Sympathetic", "Slow",
    "Unceremonious", "Humorous", "Tough", "Fraudulent", "Systematic", "Sly", "Gloomy", "Idealistic", "Unaggressive",
    "Frightening", "Tasteful", "Small-thinking", "Thorough"
  )

  def getPersonality(): String = {
    val random1 = Roller.randomInt(Personalities.length)
    val tmp = Roller.randomInt(Personalities.length)
    val random2 = if (tmp != random1) tmp else Roller.randomInt(Personalities.length)

    s"${Personalities(random1)}/${Personalities(random2)}"
  }


}
